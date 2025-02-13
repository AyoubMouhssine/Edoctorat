package com.estf.edoctorat.controllers;
import com.estf.edoctorat.dto.CommissionProfesseurDTO;
import com.estf.edoctorat.services.CommissionProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommissionProfesseurController {
    @Autowired
    private CommissionProfesseurService commissionProfesseurService;
    @GetMapping("/participant/")
    public ResponseEntity<List<CommissionProfesseurDTO>> getCommissionProfesseurs() {
        System.out.println(commissionProfesseurService.getCommissionProfesseurs());
        return ResponseEntity.ok(commissionProfesseurService.getCommissionProfesseurs());
    }
    @GetMapping("/participant/{id}")
    public ResponseEntity<CommissionProfesseurDTO> getCommissionProfesseur(@PathVariable Long id) {
        CommissionProfesseurDTO commissionProfesseurDTO = commissionProfesseurService.getCommissionProfesseur(id);
        return  commissionProfesseurDTO != null ? ResponseEntity.ok(commissionProfesseurDTO) : ResponseEntity.notFound().build();
    }
    @PostMapping("/participant")
    public ResponseEntity<CommissionProfesseurDTO> createCommissionProfesseur(@RequestBody CommissionProfesseurDTO commissionProfesseurDTO) {
        CommissionProfesseurDTO createdCommissionProfesseurDTO =  commissionProfesseurService.createCommissionProfesseur(commissionProfesseurDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(createdCommissionProfesseurDTO);
    }
    @DeleteMapping("/participant/{id}")
    public ResponseEntity<Void> deleteCommissionProfesseur(@PathVariable Long id) {
        boolean deleted = commissionProfesseurService.deleteCommissionProfesseur(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}