package com.estf.edoctorat.controllers;
import com.estf.edoctorat.dto.ProfesseurDTO;
import com.estf.edoctorat.services.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProfesseurController {

    @Autowired
    private ProfesseurService professeurService;

    @GetMapping("/get-professeurs")
    public ResponseEntity<List<ProfesseurDTO>> getProfesseurs() {
        return ResponseEntity.ok(professeurService.getProfesseurs());
    }

    @GetMapping("/get-professeurs/{id}")
    public ResponseEntity<ProfesseurDTO> getProfesseur(@PathVariable Long id) {
        ProfesseurDTO professeurDTO =  professeurService.getProfesseur(id);
        return professeurDTO != null ? ResponseEntity.ok(professeurDTO) : ResponseEntity.notFound().build();
    }
    @PostMapping("/get-professeurs")
    public ResponseEntity<ProfesseurDTO> createProfesseur(@RequestBody ProfesseurDTO professeurDTO) {
        ProfesseurDTO createdProfesseurDTO =  professeurService.createProfesseur(professeurDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(createdProfesseurDTO);
    }
}