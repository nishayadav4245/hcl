package com.pharmacy.config;

import com.pharmacy.entity.Role;
import com.pharmacy.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        List<String> roles = List.of("ROLE_USER", "ROLE_ADMIN", "ROLE_PHARMACIST");
        for (String roleName : roles) {
            if (roleRepository.findByName(roleName).isEmpty()) {
                roleRepository.save(Role.builder().name(roleName).build());
            }
        }
    }
}
