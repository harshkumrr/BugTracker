package com.bugtracker.bugtracker_backend.config;

import com.bugtracker.bugtracker_backend.entity.User;
import com.bugtracker.bugtracker_backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleDataInitializer {

    @Bean
    CommandLineRunner initSampleUsers(UserRepository userRepository) {
        return args -> {
            createUserIfMissing(userRepository, "Admin User", "admin", "admin123", "Admin");
            createUserIfMissing(userRepository, "Developer One", "dev1", "dev123", "Developer");
            createUserIfMissing(userRepository, "Tester One", "tester1", "test123", "Tester");
        };
    }

    private void createUserIfMissing(
            UserRepository userRepository,
            String name,
            String username,
            String password,
            String role
    ) {
        if (userRepository.findByUsername(username).isPresent()) {
            return;
        }

        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        userRepository.save(user);
    }
}
