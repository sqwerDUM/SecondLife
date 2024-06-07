package com.itacademy.finalprod.project_.config;

import com.itacademy.finalprod.project_.entity.Role;
import com.itacademy.finalprod.project_.repo.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner init(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName("ROLE_USER").isEmpty()) {
                Role role = new Role();
                role.setName("ROLE_USER");
                roleRepository.save(role);
            }
        };
    }
}
