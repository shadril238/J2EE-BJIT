package bjit.ursa.apigateway.filters;

import bjit.ursa.apigateway.service.JwtService;
import bjit.ursa.apigateway.util.ErrorMessages;
import bjit.ursa.apigateway.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    private final JwtService jwtService;
    private final ResponseUtil responseUtil;


    public AuthenticationFilter(JwtService jwtService,ResponseUtil responseUtil) {
        super(Config.class);
        this.jwtService = jwtService;
        this.responseUtil = responseUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Extract token from Authorization header
            HttpHeaders headers = exchange.getRequest().getHeaders();
            String authorizationHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
            String token = null;
            // check Token existence
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return responseUtil.setErrorResponse(exchange, ErrorMessages.NO_TOKEN_FOUND);
            }
            token = authorizationHeader.substring(7);
            //check  token validity
            if (!jwtService.isTokenValid(token)) {
                return responseUtil.setErrorResponse(exchange, ErrorMessages.INVALID_TOKEN);
            }
            //check authorized roles
            List<String> roles = jwtService.extractUserRoles(token);
            if (!roles.isEmpty() && roles.contains(config.getRole())) {
                return chain.filter(exchange);
            }
            if (config.getRole().equals("ANY_USER")) {
                return chain.filter(exchange);
            }

            return responseUtil.setErrorResponse(exchange, ErrorMessages.UNAUTHORIZED_MESSAGE);
        };
    }

    public static class Config {
        private String role;

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
