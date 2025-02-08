package com.estf.edoctorat.controllers;

import com.estf.edoctorat.dto.ProfesseurDTO;
import com.estf.edoctorat.dto.Result;  // Import the Result class
import com.estf.edoctorat.services.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProfesseurController {

    @Autowired
    private ProfesseurService professeurService;

    // Updated to return Result<ProfesseurDTO>
    @GetMapping("/get-professeurs/")
    public ResponseEntity<Result<ProfesseurDTO>> getProfesseurs() {
        Result<ProfesseurDTO> result = professeurService.getProfesseurs();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get-professeurs/{id}")
    public ResponseEntity<ProfesseurDTO> getProfesseur(@PathVariable Long id) {
        ProfesseurDTO professeurDTO = professeurService.getProfesseur(id);
        return professeurDTO != null ? ResponseEntity.ok(professeurDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping("/get-professeurs")
    public ResponseEntity<ProfesseurDTO> createProfesseur(@RequestBody ProfesseurDTO professeurDTO) {
        ProfesseurDTO createdProfesseurDTO = professeurService.createProfesseur(professeurDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfesseurDTO);
    }
}
