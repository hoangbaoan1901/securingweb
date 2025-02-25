package com.example.securingweb;

import com.example.securingweb.model.User;
import com.example.securingweb.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInit {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                String raw_admin_password = "admin_password";
                admin.setPassword(passwordEncoder.encode(raw_admin_password));
                admin.setRole("ADMIN");
                userRepository.save(admin);
                System.out.println(String.format("Created default ADMIN user: admin / %s", raw_admin_password));
            }

            if (userRepository.findByUsername("user").isEmpty()) {
                User user = new User();
                user.setUsername("user");
                String raw_user_password = "user_password";
                user.setPassword(passwordEncoder.encode(raw_user_password));
                user.setRole("USER");
                userRepository.save(user);
                System.out.println(String.format("Created user: user / %s", raw_user_password));
            }
        };
    }
}
