package com.shadril.rolebasedauthjwt.serviceImpl;

import com.shadril.rolebasedauthjwt.dto.request.RegisterRequest;
import com.shadril.rolebasedauthjwt.dto.request.UserAddRequest;
import com.shadril.rolebasedauthjwt.dto.request.UserUpdateRequest;
import com.shadril.rolebasedauthjwt.dto.response.RegisterResponse;
import com.shadril.rolebasedauthjwt.dto.response.UserAddResponse;
import com.shadril.rolebasedauthjwt.entity.Profile;
import com.shadril.rolebasedauthjwt.entity.Role;
import com.shadril.rolebasedauthjwt.entity.RoleType;
import com.shadril.rolebasedauthjwt.entity.User;
import com.shadril.rolebasedauthjwt.exception.RoleNotFoundException;
import com.shadril.rolebasedauthjwt.repository.RoleRepository;
import com.shadril.rolebasedauthjwt.repository.UserRepository;
import com.shadril.rolebasedauthjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.shadril.rolebasedauthjwt.util.UtilMethods.generateRandomString;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder encoder;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);

    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    @Override
    public RegisterResponse save(RegisterRequest request) {
        //user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setEnabled(true);
        user.setNonLocked(true);
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRoles(checkRoles(request.getRole()));
        //profile
        Profile profile = new Profile();
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());

        user.setProfile(profile);
        userRepository.save(user);
        //response
        return RegisterResponse.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .username(request.getUsername())
                .isAccountNonLocked(true)
                .isAccountVerified(true) // enabled is renamed here with verified attribute
                .build();
    }

    @Override
    public UserAddResponse save(UserAddRequest request) {
        //generate random username and password
        String randomUserPassword = generateRandomString(8);
        String randomUsername = request.getFirstName().toLowerCase() + "@" + generateRandomString(4);
        //user
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(randomUsername);
        // generate password
        // send email to the user with generated password
        user.setPassword(encoder.encode(randomUserPassword));
        user.setNonLocked(true);
        user.setEnabled(true);
        user.setRoles(checkRoles(request.getRole()));
        //profile
        Profile profile = new Profile();
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());

        user.setProfile(profile);
        userRepository.save(user);
        return UserAddResponse
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .username(randomUsername)
                .build();

    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public User updateUser(UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(userUpdateRequest.getId())
                .orElseThrow(() -> new UsernameNotFoundException("user not found for id: " + userUpdateRequest.getId()));
        user.setId(user.getId());
        user.setEmail(userUpdateRequest.getEmail());
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName(RoleType.valueOf(userUpdateRequest.getRole()))
                .orElseThrow(() -> new RuntimeException("Role not found for " + userUpdateRequest.getRole()));
        roles.add(role);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> existsByUserId(long trainerAccountId) {
        return userRepository.findById(trainerAccountId);
    }


    @Transactional
    @Override
    public void activateDeactivateUserAccount(User user) {
        user.setId(user.getId());
        user.setNonLocked(!user.isNonLocked());
        userRepository.save(user);
    }

    @Override
    public Optional<User> findById(long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User resetPassword(User user) {
        return userRepository.save(user);
    }

    private Set<Role> checkRoles(String stringRole) {
        Set<Role> roles = new HashSet<>();
        if (stringRole == null) {
            Role defaultRole = roleRepository.findByName(RoleType.ROLE_USER)
                    .orElseThrow(() -> new RoleNotFoundException(RoleType.ROLE_USER + " DOESN'T EXIST!"));
            roles.add(defaultRole);
        } else {
            switch (stringRole) {
                case "ROLE_USER" -> {
                    Role role = roleRepository.findByName(RoleType.ROLE_USER)
                            .orElseThrow(() -> new RoleNotFoundException(RoleType.ROLE_USER + " DOESN'T EXIST!"));
                    roles.add(role);
                }
            }
        }
        return roles;
    }


}
