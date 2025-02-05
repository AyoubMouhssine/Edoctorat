package com.estf.edoctorat.config;

import com.estf.edoctorat.models.AuthGroup;
import com.estf.edoctorat.models.Config;
import com.estf.edoctorat.models.User;
import com.estf.edoctorat.repositories.AuthGroupRepository;
import com.estf.edoctorat.repositories.ConfigRepository;
import com.estf.edoctorat.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final AuthGroupRepository authGroupRepository;
    private final ConfigRepository configRepository;
    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthGroupRepository authGroupRepository, ConfigRepository configRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = passwordEncoder;
        this.authGroupRepository = authGroupRepository;
        this.configRepository = configRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByEmail("stuff01@est.com").isEmpty()) {
            User user = new User();
            user.setFirstName("stuff");
            user.setLastName("01");
            user.setUsername("stuff 01");
            user.setDateJoined(new Date());
            user.setEmail("stuff01@est.com");
            user.setPassword(bCryptPasswordEncoder.encode("stuff01"));
            user.setIsActive(true);
            user.setIsStuff(true);
            userRepository.save(user);
            System.out.println("stuff user created!");
        }

        List<String> groupNames = List.of("candidat", "professeur", "directeur_ced", "directeur_labo", "directeur_pole", "directeur_scolarite");
        for (String name:groupNames) {
            if(authGroupRepository.findByName(name).isEmpty()){
                AuthGroup authGroup = new AuthGroup();
                authGroup.setName(name);
                authGroupRepository.save(authGroup);
            }
        }
        if (configRepository.findById(1L).isEmpty()) {
            Config config = new Config();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            config.setMaxSujetPostuler(5);
            config.setDateDebutPostulerSujetCandidat(java.sql.Date.valueOf(LocalDate.parse("01-10-2025", formatter)));
            config.setDateDebutModifierSujetProf(java.sql.Date.valueOf(LocalDate.parse("01-09-2025", formatter)));
            config.setDateFinPostulerSujetCandidat(java.sql.Date.valueOf(LocalDate.parse("15-10-2025", formatter)));
            config.setDateFinModifierSujetProf(java.sql.Date.valueOf(LocalDate.parse("20-09-2025", formatter)));
            configRepository.save(config);
        }
    }
}