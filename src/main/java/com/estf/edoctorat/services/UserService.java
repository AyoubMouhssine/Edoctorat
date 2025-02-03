package com.estf.edoctorat.services;

import com.estf.edoctorat.models.User;
import com.estf.edoctorat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public User createUser(String email, String nom, String prenom) {
        User user = new User();
        user.setEmail(email);
        user.setFirstName(nom);
        user.setLastName(prenom);
        user.setIsActive(false);
        return userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
