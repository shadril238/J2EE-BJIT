package bjit.ursa.authserver.service.impl;

import bjit.ursa.authserver.entity.AccountEntity;
import bjit.ursa.authserver.entity.RoleEntity;
import bjit.ursa.authserver.exception.AccountAlreadyExists;
import bjit.ursa.authserver.exception.InvalidAuthenticationCredentials;
import bjit.ursa.authserver.model.*;
import bjit.ursa.authserver.repositoty.AccountRepository;
import bjit.ursa.authserver.service.AccountService;
import bjit.ursa.authserver.service.JwtService;
import bjit.ursa.authserver.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    public ResponseEntity<APIResponse> register(RegisterRequest registerRequest) {
        Optional<AccountEntity> accountEntity = accountRepository.findByEmail(registerRequest.getEmail());
        if (accountEntity.isPresent()) {
            throw new AccountAlreadyExists("The requested email " + registerRequest.getEmail() + " already registered");
        } else {
            Set<RoleEntity> roles = new HashSet<>();
            registerRequest.getRoles().forEach(value -> roles.add(roleService.getRole(value)));
            AccountEntity accountEntity1 = AccountEntity.builder().email(registerRequest.getEmail()).password(passwordEncoder.encode(registerRequest.getPassword())).roles(roles).build();
            AccountEntity savedEntity = accountRepository.save(accountEntity1);
            return new ResponseEntity<>(APIResponse.builder().data(new RegisterResponse("Successfully registered with the  email " + savedEntity.getEmail() + ".")).build(), HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<APIResponse> login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (Exception ex) {
            throw new InvalidAuthenticationCredentials("Invalid Email and Password");
        }
        String jwtToken = jwtService.generateToken(userDetailsService.loadUserByUsername(loginRequest.getEmail()));
        LoginResponse loginResponse = LoginResponse.builder().token(jwtToken).build();
        return new ResponseEntity<>(APIResponse.builder().data(loginResponse).build(), HttpStatus.OK);
    }
}