package com.estf.edoctorat.services;

import com.estf.edoctorat.dto.FormationDoctoraleDTO;
import com.estf.edoctorat.dto.Result;
import com.estf.edoctorat.dto.SujetDTO;
import com.estf.edoctorat.mappers.FormationDoctoraleMapper;
import com.estf.edoctorat.mappers.SujetMapper;
import com.estf.edoctorat.models.FormationDoctorale;
import com.estf.edoctorat.models.Professeur;
import com.estf.edoctorat.models.Sujet;
import com.estf.edoctorat.models.User;
import com.estf.edoctorat.repositories.FormationDoctoraleRepository;
import com.estf.edoctorat.repositories.ProfesseurRepository;
import com.estf.edoctorat.repositories.SujetRepository;
import com.estf.edoctorat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SujetService {

    @Autowired
    private SujetRepository sujetRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfesseurRepository professeurRepository;

    @Autowired
    private SujetMapper sujetMapper;
    @Autowired
    private FormationDoctoraleRepository formationDoctoraleRepository;
    @Autowired
    private FormationDoctoraleMapper formationDoctoraleMapper;


    public Result<SujetDTO> getSujets(int limit, int offset) {
        List<Sujet> sujets = sujetRepository.findSujetsWithPagination(limit, offset);
        List<SujetDTO> sujetDTOs = sujets.stream().map(sujetMapper::toDTO).collect(Collectors.toList());
        int total = (int) sujetRepository.count(); // Get total count
        // Specify Result<SujetDTO> explicitly
        Result<SujetDTO> result = new Result<>(sujetDTOs, total);
        return result;
    }

    public Result<SujetDTO> getSujets() {
        List<Sujet> sujets = sujetRepository.findAll();
        List<SujetDTO> sujetDTOs = sujets.stream().map(sujetMapper::toDTO).collect(Collectors.toList());
        // Specify Result<SujetDTO> explicitly
        Result<SujetDTO> result = new Result<>(sujetDTOs, sujetDTOs.size()); // Using size for total count
        return result;
    }

    public Result<SujetDTO> getSujetsByProfessor(Long professorId) {
        // Retrieve the list of subjects by professor's ID
        List<Sujet> sujets = sujetRepository.findByProfesseurId(professorId);

        // Convert the list of Sujet to SujetDTO
        List<SujetDTO> sujetDTOs = sujets.stream()
                .map(sujetMapper::toDTO)
                .collect(Collectors.toList());

        // Return a Result object with the list and its size (total count)
        Result<SujetDTO> result = new Result<>(sujetDTOs, sujetDTOs.size());
        return result;
    }


    //ADD NEW SUJET

    public Result<SujetDTO> addSujet(SujetDTO sujetDTO, Authentication authentication) {
        // Step 1: Get authenticated user's email
        String email = authentication.getName();

        // Step 2: Fetch the User entity
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // Step 3: Fetch the Professeur entity
        Professeur professeur = professeurRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Professeur not found for user id: " + user.getId()));

        // Step 4: Retrieve the FormationDoctorale entity
        FormationDoctorale formationDoctorale = formationDoctoraleRepository.findById(sujetDTO.getFormationDoctoraleId())
                .orElseThrow(() -> new RuntimeException("FormationDoctorale not found"));

        // Step 5: Convert DTO to Entity
        Sujet sujet = sujetMapper.toEntity(sujetDTO);

        // Step 6: Assign the retrieved entities
        sujet.setFormationDoctorale(formationDoctorale);
        sujet.setProfesseur(professeur); // Set the authenticated professeur

        // Step 7: Save the Sujet entity
        Sujet savedSujet = sujetRepository.save(sujet);

        // Step 8: Convert back to DTO
        SujetDTO savedSujetDTO = sujetMapper.toDTO(savedSujet);

        // Step 9: Return the result
        return new Result<>(List.of(savedSujetDTO), 1);
    }

}
