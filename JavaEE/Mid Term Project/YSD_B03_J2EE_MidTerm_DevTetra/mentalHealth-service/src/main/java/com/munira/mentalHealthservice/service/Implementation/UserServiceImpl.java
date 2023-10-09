package com.munira.mentalHealthservice.service.Implementation;


import com.munira.mentalHealthservice.dto.UserDTO;
import com.munira.mentalHealthservice.networkmanager.UserFiengClient;
import com.munira.mentalHealthservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserFiengClient userFiengClient;

    @Override
    public UserDTO getCurrentUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userMail = authentication.getName();
        return userFiengClient.userDetailsByEmail(userMail);
    }
}
