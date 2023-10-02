package bjit.ursa.authserver.controller;

import bjit.ursa.authserver.model.APIResponse;
import bjit.ursa.authserver.model.LoginRequest;
import bjit.ursa.authserver.model.RegisterRequest;
import bjit.ursa.authserver.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth-server")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<APIResponse> accountRegister(@Valid @RequestBody RegisterRequest registerRequest) {
        return accountService.register(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse> accountLogin(@Valid @RequestBody LoginRequest loginRequest) {
        return accountService.login(loginRequest);
    }
}