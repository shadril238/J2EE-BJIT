package com.example.userservice.service.Implementation;

import com.example.userservice.Entity.UserEntity;
import com.example.userservice.constants.AppConstants;
import com.example.userservice.dto.UserDto;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import com.example.userservice.utils.JWTUtils;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImplementation implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserServiceImplementation(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    public UserDto createUser(UserDto user) throws Exception {
        if(userRepository.findByEmail(user.getEmail()).isPresent())
            throw new Exception("Email already exists!");
        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setAge(user.getAge());
        userEntity.setGender(user.getGender());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setRole(user.getRole());
        UserEntity storedUserDetails = userRepository.save(userEntity);
        UserDto returnedValue = new ModelMapper().map(storedUserDetails,UserDto.class);

        List<String> userRoles = new ArrayList<>();
        userRoles.add(user.getRole());

        String accessToken = JWTUtils.generateToken(userEntity.getEmail(), userRoles);
        returnedValue.setAccessToken(AppConstants.TOKEN_PREFIX + accessToken);
        return returnedValue;
    }
    @Override
    public UserDto getUser(String email) throws Exception {
        UserEntity userEntity = userRepository
                .findByEmail(email)
                .orElseThrow(()->new Exception("User not found!"));
        return new ModelMapper().map(userEntity, UserDto.class);
    }
    @Override
    public UserDto getUserByUserId(Long userId) throws Exception {
        UserEntity userEntity = userRepository
                .findByUserId(userId)
                .orElseThrow(()->new Exception("User not found!"));
        return new ModelMapper().map(userEntity, UserDto.class);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User not found!"));
        return new User(userEntity.getEmail(),userEntity.getPassword(),
                true,true,true,true,new ArrayList<>());
    }


    public UserDto getUserProfile() throws Exception {
        UserEntity userEntity = getCurrentUser();
        return new ModelMapper().map(userEntity, UserDto.class);
    }

    private UserEntity getCurrentUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new Exception(AppConstants.TOKEN_INVALID));
    }

    public void editUserProfile(UserDto userDto) throws Exception {

        UserEntity userEntity = userRepository.findByUserId(getCurrentUser().getUserId())
                .orElseThrow(()->new Exception("User not found!"));

        userEntity.setName(userDto.getName() != null ? userDto.getName() : userEntity.getName());
        userEntity.setAge(userDto.getAge() != null ? userDto.getAge() : userEntity.getAge());
        userEntity.setGender(userDto.getGender() != null ? userDto.getGender() : userEntity.getGender());
        userEntity.setRole(userDto.getRole() != null ? userDto.getRole() : userEntity.getRole());

        userRepository.save(userEntity);
    }
}
