package bjit.ursa.authserver.service;

import bjit.ursa.authserver.model.APIResponse;
import bjit.ursa.authserver.model.LoginRequest;
import bjit.ursa.authserver.model.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<APIResponse> register(RegisterRequest registerRequest);

    ResponseEntity<APIResponse> login(LoginRequest loginRequest);
}