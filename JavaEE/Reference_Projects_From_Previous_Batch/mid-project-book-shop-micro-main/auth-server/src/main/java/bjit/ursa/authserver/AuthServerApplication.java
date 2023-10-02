package bjit.ursa.authserver;

import bjit.ursa.authserver.entity.RoleEnum;
import bjit.ursa.authserver.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleService roleService) {
        return args -> {
            roleService.addRole(RoleEnum.ADMIN);
            roleService.addRole(RoleEnum.USER);
        };
    }
}