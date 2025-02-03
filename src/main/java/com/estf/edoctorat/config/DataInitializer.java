package com.estf.edoctorat.config;

import com.estf.edoctorat.models.User;
import com.estf.edoctorat.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByEmail("admin@example.com").isEmpty()) {
            User user = new User();
            user.setFirstName("stuff");
            user.setLastName("01");
            user.setDateJoined(new Date());
            user.setEmail("stuff01@est.com");
            user.setPassword(bCryptPasswordEncoder.encode("stuff01"));
            user.setIsActive(true);
            user.setIsStuff(true);
            userRepository.save(user);
            System.out.println("stuff user created!");
        }
    }
}