package com.estf.edoctorat.controllers;
import com.estf.edoctorat.dto.ProfesseurLaboratoireDTO;
import com.estf.edoctorat.services.ProfesseurLaboratoireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProfesseurLaboratoireController {
    @Autowired
    private ProfesseurLaboratoireService professeurLaboratoireService;

    @GetMapping("/labo_professeur")
    public ResponseEntity<List<ProfesseurLaboratoireDTO>> getProfesseurLaboratoires() {
        return ResponseEntity.ok(professeurLaboratoireService.getProfesseurLaboratoires());
    }
    @GetMapping("/labo_professeur/{id}")
    public ResponseEntity<ProfesseurLaboratoireDTO> getProfesseurLaboratoire(@PathVariable Long id) {
        ProfesseurLaboratoireDTO professeurLaboratoireDTO =  professeurLaboratoireService.getProfesseurLaboratoire(id);
        return  professeurLaboratoireDTO != null ? ResponseEntity.ok(professeurLaboratoireDTO) : ResponseEntity.notFound().build();
    }
    @PostMapping("/labo_professeur")
    public ResponseEntity<ProfesseurLaboratoireDTO> createProfesseurLaboratoire(@RequestBody ProfesseurLaboratoireDTO professeurLaboratoireDTO) {
        ProfesseurLaboratoireDTO createdProfesseurLaboratoireDTO =  professeurLaboratoireService.createProfesseurLaboratoire(professeurLaboratoireDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfesseurLaboratoireDTO);
    }
}