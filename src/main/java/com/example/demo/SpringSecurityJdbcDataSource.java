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
            User user = new User("bart@gmail.com",
                    "password",
                    "Bart",
                    "Simpson",
                    30312567,
                    "123 Main Street",
                    "Springfield",
                    12345);
            Role userRole = new Role("bart", "ROLE_USER");

            userRepository.save(user);
            roleRepository.save(userRole);

            User admin = new User("super", "super", "Super", "Man", true);
            Role adminRole = new Role("super", "ROLE_ADMIN");

            userRepository.save(admin);
            roleRepository.save(adminRole);
        };
    }
}
