package com.shadril.customerservice.config;

import com.shadril.customerservice.client.AccountClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {
    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;


    @Bean
    public WebClient accountWebClient(){
        return WebClient.builder()
                .baseUrl("http://account-service")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public AccountClient accountClient(){
        HttpServiceProxyFactory httpServiceProxyFactory= HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(accountWebClient()))
                .build();
        return httpServiceProxyFactory.createClient(AccountClient.class);
    }
}
