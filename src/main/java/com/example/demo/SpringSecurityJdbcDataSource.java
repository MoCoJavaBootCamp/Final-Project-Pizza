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
    public CommandLineRunner run(UserRepository userRepository,
                                 RoleRepository roleRepository,
                                 PizzaRepository pizzaRepository,
                                 ToppingRepository toppingRepository,
                                 ReportRepository reportRepository) throws Exception {
        return (String[] args) -> {
            User user = new User("bart@gmail.com",
                    "password",
                    "Bart",
                    "Simpson",
                    30312567,
                    "123 Main Street",
                    "Springfield",
                    12345);
            Role userRole = new Role("bart@gmail.com", "ROLE_USER");

            userRepository.save(user);
            roleRepository.save(userRole);

            User admin = new User("admin@domain.com",
                    "admin",
                    "Admin",
                    "Account",
                    555555555,
                    "123 Admin Street",
                    "Admin City",
                    12345);
            Role adminRole = new Role("admin@domain.com", "ROLE_ADMIN");

            userRepository.save(admin);
            roleRepository.save(adminRole);
        };
    }
}
