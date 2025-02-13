package com.estf.edoctorat.services;
import com.estf.edoctorat.dto.CommissionProfesseurDTO;
import com.estf.edoctorat.mappers.CommissionProfesseurMapper;
import com.estf.edoctorat.models.CommissionProfesseur;
import com.estf.edoctorat.models.Professeur;
import com.estf.edoctorat.models.User;
import com.estf.edoctorat.repositories.CommissionProfesseurRepository;
import com.estf.edoctorat.repositories.ProfesseurRepository;
import com.estf.edoctorat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommissionProfesseurService {
    @Autowired
    private CommissionProfesseurRepository commissionProfesseurRepository;
    @Autowired
    private UserRepository userRepository; // Need this to find the user
    @Autowired
    private ProfesseurRepository professeurRepository;
    private final CommissionProfesseurMapper commissionProfesseurMapper = CommissionProfesseurMapper.INSTANCE;


    public List<CommissionProfesseurDTO> getCommissionProfesseurs() {
        return commissionProfesseurRepository.findAll().stream()
                .map(commissionProfesseurMapper::commissionProfesseurToCommissionProfesseurDTO)
                .collect(Collectors.toList());
    }


    public List<CommissionProfesseurDTO> getCommissionByProfesseur(Authentication authentication) {
        String email = authentication.getName();

        // Step 2: Fetch the User entity
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // Step 3: Fetch the Professeur entity
        Professeur professeur = professeurRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Professeur not found for user id: " + user.getId()));


        List<CommissionProfesseur> commissionProfesseurs = commissionProfesseurRepository.findByProfesseurId(professeur.getId());

        // Use the mapper
        return (commissionProfesseurs.stream()
                .map(commissionProfesseurMapper::commissionProfesseurToCommissionProfesseurDTO)
                .collect(Collectors.toList()));
    }

    public boolean deleteCommissionProfesseur(Long id) {
        if(commissionProfesseurRepository.existsById(id)){
            commissionProfesseurRepository.deleteById(id);
            return true;
        }
        else return false;
    }




}