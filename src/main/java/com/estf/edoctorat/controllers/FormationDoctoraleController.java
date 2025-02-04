package com.estf.edoctorat.controllers;
import com.estf.edoctorat.dto.FormationDoctoraleDTO;
import com.estf.edoctorat.services.FormationDoctoraleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FormationDoctoraleController {
    @Autowired
    private FormationDoctoraleService formationDoctoraleService;
    @GetMapping("/formation-doctorale")
    public ResponseEntity<List<FormationDoctoraleDTO>> getFormationDoctorales() {
        return ResponseEntity.ok(formationDoctoraleService.getFormationDoctorales());
    }
    @GetMapping("/formation-doctorale/{id}")
    public ResponseEntity<FormationDoctoraleDTO> getFormationDoctorale(@PathVariable Long id) {
        FormationDoctoraleDTO formationDoctoraleDTO =  formationDoctoraleService.getFormationDoctorale(id);
        return  formationDoctoraleDTO != null ? ResponseEntity.ok(formationDoctoraleDTO) : ResponseEntity.notFound().build();
    }
    @PostMapping("/formation-doctorale")
    public ResponseEntity<FormationDoctoraleDTO> createFormationDoctorale(@RequestBody FormationDoctoraleDTO formationDoctoraleDTO) {
        FormationDoctoraleDTO createdFormationDoctoraleDTO =  formationDoctoraleService.createFormationDoctorale(formationDoctoraleDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(createdFormationDoctoraleDTO);
    }
}