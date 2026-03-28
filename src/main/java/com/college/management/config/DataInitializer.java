package com.college.management.config;
import com.college.management.entity.Role;
import com.college.management.entity.User;
import com.college.management.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class DataInitializer implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository; this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void run(String... args) {
        if (!userRepository.existsByEmail("admin@college.edu")) {
            User admin = new User();
            admin.setName("System Admin");
            admin.setEmail("admin@college.edu");
            admin.setPassword(passwordEncoder.encode("Admin@123"));
            admin.setRole(Role.ADMIN);
            admin.setActive(true);
            userRepository.save(admin);
            log.info("Default admin created: admin@college.edu / Admin@123");
        }
    }
}
