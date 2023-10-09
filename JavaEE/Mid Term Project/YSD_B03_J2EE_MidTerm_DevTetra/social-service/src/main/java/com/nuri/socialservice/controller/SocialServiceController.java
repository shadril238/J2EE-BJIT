package com.nuri.socialservice.controller;

import com.nuri.socialservice.dto.*;
import com.nuri.socialservice.exception.GroupNotMatchException;
import com.nuri.socialservice.exception.NameAlreadyExistsException;
import com.nuri.socialservice.exception.NullValueException;
import com.nuri.socialservice.exception.ValueNotFoundException;
import com.nuri.socialservice.service.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community")
public class SocialServiceController {

    @Autowired
    private SocialService socialService;

    @PostMapping("/groups/create")
    public String createGroup(@RequestBody GroupDto groupDto) throws NullValueException, NameAlreadyExistsException {
        if (groupDto.getGroupName() == null){
            throw new NullValueException("Group name should not be empty.");
        }
        socialService.createGroup(groupDto);
        return "Group has been created";
    }

    @PostMapping("/groups/join/{groupId}")
    public String joinGroup(@PathVariable Long groupId)
            throws ValueNotFoundException, NameAlreadyExistsException {
        socialService.joinGroup(groupId);
        return "You can now post on group " + groupId;
    }

    @PostMapping("/posts/{groupId}")
    public PostDto postOnGroup(@PathVariable Long groupId,
                               @RequestBody PostDto postDto) throws ValueNotFoundException, GroupNotMatchException {
        return socialService.postOnGroup(groupId, postDto);
    }

    @PostMapping("/like/{postId}")
    public LikeDto likeOnPost(@PathVariable Long postId) throws ValueNotFoundException, GroupNotMatchException, NameAlreadyExistsException {
        return socialService.likeOnPost(postId);
    }

    @PostMapping("/comment/{postId}")
    public CommentDto commentOnPost(@PathVariable Long postId,
                                    @RequestBody CommentDto commentDto) throws ValueNotFoundException, GroupNotMatchException {
        return socialService.commentOnPost(postId, commentDto);
    }

    @PostMapping("/follower/{followerUserId}")
    public FollowerDto followIndividualUser(@PathVariable Long followerUserId) throws NameAlreadyExistsException {
        return socialService.followIndividualUser(followerUserId);
    }

    @DeleteMapping("/unfollow/{followerUserId}")
    public String unfollowIndividualUser(@PathVariable Long followerUserId) throws ValueNotFoundException {
        socialService.unfollowIndividualUser(followerUserId);
        return "You unfollowed user " + followerUserId;
    }

    @GetMapping("/get-follower")
    public String countFollower(){
        Long count = socialService.countIndividualFollower();
        return "You are followed by " + count + " user.";
    }

    @DeleteMapping("/delete-post/{postId}")
    public String deletePost(@PathVariable Long postId) throws ValueNotFoundException, NullValueException {
        socialService.deletePost(postId);
        return "Successfully deleted post " + postId;
    }

    @GetMapping("/post-info/{postId}")
    public IndividualPostInfoDto postInfo(@PathVariable Long postId)
            throws ValueNotFoundException, GroupNotMatchException {
        return socialService.getInfoOfIndividualPost(postId);
    }

    @GetMapping("/group-posts/{groupId}")
    public List<IndividualPostInfoDto> groupPosts(@PathVariable Long groupId)
            throws ValueNotFoundException, GroupNotMatchException {
        return socialService.getGroupPosts(groupId);
    }
}
