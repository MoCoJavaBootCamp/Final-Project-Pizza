package com.example.demo;

import com.example.demo.repository.*;
import com.example.demo.tables.Role;
import com.example.demo.tables.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringSecurityJdbcDataSource {


    @Bean
    public CommandLineRunner run(UserRepository userRepository,
                                 RoleRepository roleRepository,
                                 PizzaRepository pizzaRepository,
                                 ToppingRepository toppingRepository,
                                 ReportRepository reportRepository) throws Exception {
        return (String[] args) -> {
            User user = new User(
                    "bart@gmail.com",
                    "password",
                    "Bart",
                    "Simpson",
                    30312567,
                    "123 Main Street",
                    "Springfield",
                    12345,
                    true);
            Role userRole = new Role("bart@gmail.com", "ROLE_USER");
            userRepository.save(user);
            roleRepository.save(userRole);

            User sue = new User(
                    "shan@montgomerycollege.edu",
                    "1234",
                    "Sue",
                    "Han",
                    30312567,
                    "123 Main Street",
                    "Rockville",
                    12345,
                    true);
            Role sueRole = new Role("shan@montgomerycollege.edu", "ROLE_USER");
            userRepository.save(sue);
            roleRepository.save(sueRole);

            User jaeha = new User(
                    "jaeha@gmail.com",
                    "1234",
                    "Jaeha ",
                    "Song",
                    30312567,
                    "123 Rockville Pike",
                    "Rockville",
                    12345,
                    true);
            Role jaehaRole = new Role("jaeha@gmail.com", "ROLE_USER");
            userRepository.save(jaeha);
            roleRepository.save(jaehaRole);

            User eyasu = new User(
                    "eyasu@gmail.com",
                    "1234",
                    "Eyasu",
                    "Haile",
                    30312567,
                    "123 Frederick Ave",
                    "Gaithersburg",
                    12345,
                    true);
            Role eyasuRole = new Role("eyasu@gmail.com", "ROLE_USER");
            userRepository.save(eyasu);
            roleRepository.save(eyasuRole);

            User andrew = new User(
                    "drewdoolittle@gmail.com",
                    "1234",
                    "Andrew",
                    "Doolittle",
                    30312567,
                    "123 Wisconsin Ave",
                    "Bethesda",
                    12345,
                    true);
            Role andrewRole = new Role("drewdoolittle@gmail.com", "ROLE_USER");
            userRepository.save(andrew);
            roleRepository.save(andrewRole);

            User admin = new User(
                    "admin@domain.com",
                    "admin",
                    "Admin",
                    "Account",
                    555555555,
                    "123 Admin Street",
                    "Admin City",
                    12345,
                    true);
            Role adminRole = new Role("admin@domain.com", "ROLE_ADMIN");
            userRepository.save(admin);
            roleRepository.save(adminRole);
        };
    }
}
