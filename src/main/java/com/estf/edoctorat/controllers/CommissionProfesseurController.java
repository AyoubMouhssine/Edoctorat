package com.estf.edoctorat.controllers;
import com.estf.edoctorat.dto.CommissionProfesseurDTO;
import com.estf.edoctorat.services.CommissionProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommissionProfesseurController {
    @Autowired
    private CommissionProfesseurService commissionProfesseurService;

    // GET ALL COMMISSION
    @GetMapping("/commission/")
    public ResponseEntity<List<CommissionProfesseurDTO>> getCommissionProfesseurs() {
        System.out.println(commissionProfesseurService.getCommissionProfesseurs());
        return ResponseEntity.ok(commissionProfesseurService.getCommissionProfesseurs());
}

    // GET COMMISION FOR CURRENT PROF

    @GetMapping("/participant/")
    public ResponseEntity<List<CommissionProfesseurDTO>> getCommissionsForProfesseur(Authentication authentication) { // inject Authentication
        try {
            List<CommissionProfesseurDTO> commissions = commissionProfesseurService.getCommissionByProfesseur(authentication);
            return ResponseEntity.ok(commissions);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Or return an error message
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Handle user/professor not found
        }
    }


    @PostMapping("/participant")
//    public ResponseEntity<CommissionProfesseurDTO> createCommissionProfesseur(@RequestBody CommissionProfesseurDTO commissionProfesseurDTO) {
//        CommissionProfesseurDTO createdCommissionProfesseurDTO =  commissionProfesseurService.createCommissionProfesseur(commissionProfesseurDTO);
//        return  ResponseEntity.status(HttpStatus.CREATED).body(createdCommissionProfesseurDTO);
//    }
    @DeleteMapping("/participant/{id}")
    public ResponseEntity<Void> deleteCommissionProfesseur(@PathVariable Long id) {
        boolean deleted = commissionProfesseurService.deleteCommissionProfesseur(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}