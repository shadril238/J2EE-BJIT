package com.nuri.socialservice.service.Implementation;

import com.nuri.socialservice.dto.*;
import com.nuri.socialservice.entity.*;
import com.nuri.socialservice.exception.GroupNotMatchException;
import com.nuri.socialservice.exception.NameAlreadyExistsException;
import com.nuri.socialservice.exception.NullValueException;
import com.nuri.socialservice.exception.ValueNotFoundException;
import com.nuri.socialservice.networkmanager.UserFeingClient;
import com.nuri.socialservice.repository.*;
import com.nuri.socialservice.service.SocialService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SocialServiceImplementation implements SocialService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private FollowerRepository followerRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserFeingClient userFeingClient;

    private UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userMail = authentication.getName();
        return userFeingClient.userDetailsByEmail(userMail);
    }

    private Long getCurrentUserId() {
        return getCurrentUser().getUserId();
    }

    public void createGroup(GroupDto groupDto) throws NameAlreadyExistsException {
        Optional<Group> newGroupForGroupId1 = groupRepository.findByGroupName(groupDto.getGroupName());
        if (newGroupForGroupId1.isPresent()){
            throw new NameAlreadyExistsException("Name Already exists.");
        }

        Group newGroup = new Group();
        newGroup.setGroupName(groupDto.getGroupName());
        newGroup.setGroupDescription(groupDto.getGroupDescription());
        newGroup.setUserId(getCurrentUserId());

        groupRepository.save(newGroup);

        Group newGroupForGroupId = groupRepository.findByGroupNameAndUserId(groupDto.getGroupName(), getCurrentUserId());

        GroupMember newGroupMember = new GroupMember();
        newGroupMember.setGroupId(newGroupForGroupId.getId());
        newGroupMember.setUserId(getCurrentUserId());

        groupMemberRepository.save(newGroupMember);
    }

    public void joinGroup(Long groupId) throws ValueNotFoundException, NameAlreadyExistsException {
        Group newGroupForGroupId = groupRepository.findById(groupId)
                .orElseThrow(() -> new ValueNotFoundException("There is no group on this ID."));

        Optional<GroupMember> groupMember = groupMemberRepository.findByUserIdAndGroupId(getCurrentUserId(), groupId);

        if (groupMember.isPresent()){
            throw new NameAlreadyExistsException("You have already joined in this group.");
        }

        GroupMember newGroupMember = new GroupMember();
        newGroupMember.setGroupId(groupId);
        newGroupMember.setUserId(getCurrentUserId());

        groupMemberRepository.save(newGroupMember);
    }

    public PostDto postOnGroup(Long groupId, PostDto postDto) throws ValueNotFoundException, GroupNotMatchException {
        Group newGroupForGroupId = groupRepository.findById(groupId)
                .orElseThrow(() -> new ValueNotFoundException("There is no group on this ID."));

        GroupMember groupMember = groupMemberRepository.findByUserIdAndGroupId(getCurrentUserId(), groupId)
                .orElseThrow(() -> new GroupNotMatchException("You are not a member of this group."));

        Post newPost = new Post();
        newPost.setGroupId(groupId);
        newPost.setUserId(getCurrentUserId());
        newPost.setDateTime(new Date());
        newPost.setDescription(postDto.getDescription());

        postRepository.save(newPost);
        return new ModelMapper().map(newPost,PostDto.class);
    }

    public LikeDto likeOnPost(Long postId) throws ValueNotFoundException, GroupNotMatchException, NameAlreadyExistsException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ValueNotFoundException("Post Id not valid."));

        Optional<Like> like = likeRepository.findByUserIdAndPostId(getCurrentUserId(), postId);
        if (like.isPresent()){
            throw new NameAlreadyExistsException("You have already given like on this post");
        }

        GroupMember checkedGroupPost = groupMemberRepository
                .findByUserIdAndGroupId(getCurrentUserId(),post.getGroupId())
                .orElseThrow(() -> new GroupNotMatchException("You are not member of this group."));

        Like newLike = new Like();
        newLike.setPostId(postId);
        newLike.setUserId(getCurrentUserId());
        newLike.setDateTime(new Date());

        likeRepository.save(newLike);
        return new ModelMapper().map(newLike, LikeDto.class);
    }

    public CommentDto commentOnPost(Long postId, CommentDto commentDto) throws ValueNotFoundException, GroupNotMatchException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ValueNotFoundException("Post Id not valid."));

        GroupMember checkedGroupPost = groupMemberRepository
                .findByUserIdAndGroupId(getCurrentUserId(),post.getGroupId())
                .orElseThrow(() -> new GroupNotMatchException("You are not member of this group."));

        Comment newComment = new Comment();
        newComment.setPostId(postId);
        newComment.setUserIdGaveComment(getCurrentUserId());
        newComment.setDateTime(new Date());
        newComment.setCommentDetail(commentDto.getCommentDetail());

        commentRepository.save(newComment);
        return new ModelMapper().map(newComment, CommentDto.class);
    }

    public FollowerDto followIndividualUser(Long followerUserId) throws NameAlreadyExistsException {
        Optional<Follower> checkedExistence = followerRepository.findByFollowerUserIdAndUserId(followerUserId, getCurrentUserId());

        if (checkedExistence.isPresent()){
            throw new NameAlreadyExistsException("You have already followed this user.");
        }

        Follower newFollower = new Follower();
        newFollower.setUserId(getCurrentUserId());
        newFollower.setFollowerUserId(followerUserId);

        followerRepository.save(newFollower);
        return new ModelMapper().map(newFollower, FollowerDto.class);
    }

    public void unfollowIndividualUser(Long followerUserId) throws ValueNotFoundException {
        Follower checkedExistence = followerRepository
                .findByFollowerUserIdAndUserId(followerUserId, getCurrentUserId())
                .orElseThrow(() -> new ValueNotFoundException("You are not a follower of this user."));

        followerRepository.delete(checkedExistence);
    }

    public Long countIndividualFollower(){
        return followerRepository.countByFollowerUserId(getCurrentUserId());
    }

    public IndividualPostInfoDto getInfoOfIndividualPost(Long postId) throws ValueNotFoundException, GroupNotMatchException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ValueNotFoundException("Post Id not valid."));

        GroupMember checkedGroupPost = groupMemberRepository
                .findByUserIdAndGroupId(getCurrentUserId(),post.getGroupId())
                .orElseThrow(() -> new GroupNotMatchException("You are not member of this group."));

        Long countLikes = likeRepository.countByPostId(postId);
        List<Comment> comments = commentRepository.findAllByPostId(postId);

        IndividualPostInfoDto individualPostInfoDto = new IndividualPostInfoDto(
                postId,
                post.getDescription(),
                countLikes,
                comments
        );

        return individualPostInfoDto;
    }

    public List<IndividualPostInfoDto> getGroupPosts(Long groupId) throws ValueNotFoundException, GroupNotMatchException {
        Group newGroupForGroupId = groupRepository.findById(groupId)
                .orElseThrow(() -> new ValueNotFoundException("There is no group on this ID."));

        GroupMember checkedGroupPost = groupMemberRepository
                .findByUserIdAndGroupId(getCurrentUserId(),groupId)
                .orElseThrow(() -> new GroupNotMatchException("You are not member of this group."));

        List<Post> postList = postRepository.findAllByGroupId(groupId);
        List<IndividualPostInfoDto> individualPostInfoDto = new ArrayList<>();

        for (Post post : postList) {
            IndividualPostInfoDto infoDto = getInfoOfIndividualPost(post.getId());
            individualPostInfoDto.add(infoDto);
        }

        return individualPostInfoDto;
    }

    public void deletePost(Long postId) throws ValueNotFoundException, NullValueException {
        Post checkedValidation = postRepository.findById(postId)
                .orElseThrow(() -> new NullValueException("Post Id not valid."));

        Post checkedUserAuthority = postRepository.findByIdAndUserId(postId, getCurrentUserId())
                .orElseThrow(() -> new ValueNotFoundException("You are not allowed to delete the post."));

        List<Like> likeList = likeRepository.findAllByPostId(postId);
        likeRepository.deleteAll(likeList);

        List<Comment> commentList = commentRepository.findAllByPostId(postId);
        commentRepository.deleteAll(commentList);

        postRepository.delete(checkedUserAuthority);
    }
}
