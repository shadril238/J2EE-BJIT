package bjit.ursa.apigateway.filters;


import bjit.ursa.apigateway.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InternalFilter extends AbstractGatewayFilterFactory<InternalFilter.Config> {

    @Value("${INTERNAL_KEY}")
    private String INTERNAL_KEY;
    public InternalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Extract token from Authorization header
            HttpHeaders headers = exchange.getRequest().getHeaders();
            String authorizationHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
            String message = headers.getFirst("ALLOWED");
            if(message!= null && message.equals(INTERNAL_KEY)){
                return chain.filter(exchange);
            }
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        };
    }

    public static class Config {

    }
}
