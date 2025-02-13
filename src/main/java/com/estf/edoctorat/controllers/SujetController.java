package com.estf.edoctorat.controllers;

import com.estf.edoctorat.dto.SujetDTO;
import com.estf.edoctorat.models.FormationDoctorale;
import com.estf.edoctorat.models.Professeur;
import com.estf.edoctorat.models.Sujet;
import com.estf.edoctorat.models.User;
import com.estf.edoctorat.repositories.FormationDoctoraleRepository;
import com.estf.edoctorat.repositories.ProfesseurRepository;
import com.estf.edoctorat.repositories.SujetRepository;
import com.estf.edoctorat.repositories.UserRepository;
import com.estf.edoctorat.services.SujetService;
import com.estf.edoctorat.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SujetController {

    @Autowired
    private SujetService sujetService;
    @Autowired
    private SujetRepository sujetRepository;
    @Autowired
    private FormationDoctoraleRepository formationDoctoraleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfesseurRepository professeurRepository;

    @GetMapping("/sujets/")
    public ResponseEntity<Result<SujetDTO>> getAllSujets(Authentication authentication) {

        String email = authentication.getName();

        // Step 2: Fetch the User entity
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // Step 3: Fetch the Professeur entity
        Professeur professeur = professeurRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Professeur not found for user id: " + user.getId()));

        Result<SujetDTO> result = sujetService.getSujetsByProfessor(professeur.getId());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/sujetslabo/")
//    public ResponseEntity<Result<SujetDTO>> getSujets(@RequestParam(defaultValue = "50") int limit,
//                                                      @RequestParam(defaultValue = "0") int offset) {
//        Result<SujetDTO> result = sujetService.getSujets(limit, offset);
//        return ResponseEntity.ok(result);
//    }
    public ResponseEntity<Result<SujetDTO>> getSujets() {
        Result<SujetDTO> result = sujetService.getSujets();
//        System.out.println("This is the result =>  " + result.getResults());
        return ResponseEntity.ok(result);
    }


    @PostMapping("/sujets/")
    public Result<SujetDTO> addSujet(@RequestBody SujetDTO sujetDTO) {
        return sujetService.addSujet(sujetDTO);
    }

    @DeleteMapping("/sujets/{id}/")
    public ResponseEntity<?> deleteSujet(@PathVariable Long id) {
        Optional<Sujet> sujet = sujetRepository.findById(id);
        if (sujet.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        sujetRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // Successfully deleted
    }

    @PutMapping("/sujets/{id}/")
    public ResponseEntity<?> updateSujet(@RequestBody Map<String, Object> updatedSujetData, @PathVariable Long id) {
        Optional<Sujet> sujetOptional = sujetRepository.findById(id);
        if (sujetOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Sujet sujet = sujetOptional.get();

        // Update fields like titre and description
        sujet.setTitre((String) updatedSujetData.get("titre"));
        sujet.setDescription((String) updatedSujetData.get("description"));

        // Convert formationDoctoraleId to FormationDoctorale
        Long formationDoctoraleId = Long.parseLong((String) updatedSujetData.get("formationDoctoraleId"));
        Optional<FormationDoctorale> formationDoctoraleOptional = formationDoctoraleRepository.findById(formationDoctoraleId);
        if (formationDoctoraleOptional.isPresent()) {
            sujet.setFormationDoctorale(formationDoctoraleOptional.get());
        } else {
            return ResponseEntity.badRequest().body("FormationDoctorale not found");
        }

        // Save the updated sujet
        sujetRepository.save(sujet);

        return ResponseEntity.ok(sujet);  // Return the updated subject
    }

}
