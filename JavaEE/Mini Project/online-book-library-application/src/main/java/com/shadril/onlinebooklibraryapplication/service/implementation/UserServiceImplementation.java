package com.shadril.onlinebooklibraryapplication.service.implementation;


import com.shadril.onlinebooklibraryapplication.constants.AppConstants;
import com.shadril.onlinebooklibraryapplication.dto.UserDTO;
import com.shadril.onlinebooklibraryapplication.entity.User;
import com.shadril.onlinebooklibraryapplication.exception.EmailAlreadyExistsException;
import com.shadril.onlinebooklibraryapplication.exception.UserNotFoundException;
import com.shadril.onlinebooklibraryapplication.exception.UserNotValidException;
import com.shadril.onlinebooklibraryapplication.repository.UserRepository;
import com.shadril.onlinebooklibraryapplication.service.UserService;
import com.shadril.onlinebooklibraryapplication.utils.JWTUtils;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class UserServiceImplementation implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImplementation() {
    }


    @Override
    public UserDTO createUser(UserDTO user)
            throws EmailAlreadyExistsException {
        if(userRepository.findByEmail(user.getEmail()).isPresent())
            throw new EmailAlreadyExistsException("Email already exists!");

        ModelMapper modelMapper = new ModelMapper();
        User userEntity = new User();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setAddress(user.getAddress());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setRole(user.getRole());
        User storedUserDetails = userRepository.save(userEntity);
        UserDTO returnedValue = modelMapper.map(storedUserDetails,UserDTO.class);
        String accessToken = JWTUtils.generateToken(userEntity.getEmail());
        returnedValue.setAccessToken(AppConstants.TOKEN_PREFIX + accessToken);
        return returnedValue;
    }

    @Override
    public UserDTO getUser(String email) {
        User userEntity = userRepository.findByEmail(email).get();
        if(userEntity == null) throw new UsernameNotFoundException("No record found");
        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(userEntity,returnValue);
        return returnValue;
    }

    @Override
    public UserDTO getUserByUserId(Long userId) throws UserNotFoundException {
        UserDTO returnValue = new UserDTO();
        Optional<User> userEntity = userRepository.findById(userId);
        if (userEntity.isEmpty()) throw new UserNotFoundException("User Id does not exists!");
        BeanUtils.copyProperties(userEntity.get(),returnValue);
        return returnValue;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
        User userEntity = userRepository.findByEmail(email).get();
        if(userEntity==null) throw new UsernameNotFoundException("User Email does not exists!");
        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(),userEntity.getPassword(),
                true,true,true, true,new ArrayList<>());
    }

    @Override
    public UserDTO getCurrUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        Long currUserId = user.orElseThrow(() -> new UserNotFoundException("User not found")).getId();
        String currUserRole = user.get().getRole();
        Optional<User> userFromId = userRepository.findById(currUserId);
        Long userId = userFromId.orElseThrow(() -> new UserNotFoundException("User not found")).getId();
        if (currUserRole.equals("CUSTOMER") && !currUserId.equals(userId)) {
            throw new UserNotValidException("Not a valid customer...");
        }
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(user, UserDTO.class);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        if(userList.isEmpty()) throw new UserNotFoundException("No users found!");
        return userList;
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDto) {
        Optional<User> userEntity = userRepository.findById(userId);
        if (userEntity.isEmpty()) throw new UserNotFoundException("User Id does not exists!");
        userEntity.get().setFirstName(userDto.getFirstName());
        userEntity.get().setLastName(userDto.getLastName());
        userEntity.get().setAddress(userDto.getAddress());
//        userEntity.get().setEmail(userDto.getEmail());
//        userEntity.get().setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userEntity.get().setRole(userDto.getRole());
        User updatedUserDetails = userRepository.save(userEntity.get());
        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(updatedUserDetails,returnValue);
        return returnValue;
    }
}
