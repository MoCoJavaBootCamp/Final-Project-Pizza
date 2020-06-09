package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringSecurityJdbcDataSource {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJdbcDataSource.class, args);
    }

    @Bean
    public CommandLineRunner run(UserRepository userRepository, RoleRepository roleRepository) throws Exception {
        return (String[] args) -> {
            User user = new User("user@domain.com", "user", "User",
                    "Account",202222222, "may st", "ssp",20005, true);
            Role userRole = new Role("user@domain.com", "ROLE_USER");

            userRepository.save(user);
            roleRepository.save(userRole);

            User admin = new User("admin@domain.com", "admin", "Admin",
                    "Account", 202222222,
                    "123 Admin Street",
                    "Admin City", 90210, true);
            Role adminRole = new Role("admin@domain.com", "ROLE_ADMIN");

            userRepository.save(admin);
            roleRepository.save(adminRole);
        };
    }
}
