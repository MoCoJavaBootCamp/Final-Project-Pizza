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
            User user = new User("guest@domain.com", "guest", "Guest", "Account", true);
            Role userRole = new Role("guest@domain.com", "ROLE_USER");

            userRepository.save(user);
            roleRepository.save(userRole);

            User admin = new User("super@domain.com", "super", "Super", "Man", true);
            Role adminRole = new Role("super@domain.com", "ROLE_ADMIN");

            userRepository.save(admin);
            roleRepository.save(adminRole);
        };
    }
}
