package com.estf.edoctorat.controllers;
import com.estf.edoctorat.dto.LaboratoireDTO;
import com.estf.edoctorat.services.LaboratoireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LaboratoireController {
    @Autowired
    private LaboratoireService laboratoireService;
    @GetMapping("/laboratoires")
    public ResponseEntity<List<LaboratoireDTO>> getLaboratoires() {
        return ResponseEntity.ok(laboratoireService.getLaboratoires());
    }
    @GetMapping("/laboratoires/{id}")
    public ResponseEntity<LaboratoireDTO> getLaboratoire(@PathVariable Long id) {
        LaboratoireDTO laboratoireDTO = laboratoireService.getLaboratoire(id);
        return  laboratoireDTO != null ? ResponseEntity.ok(laboratoireDTO) : ResponseEntity.notFound().build();
    }
    @PostMapping("/laboratoires")
    public ResponseEntity<LaboratoireDTO> createLaboratoire(@RequestBody LaboratoireDTO laboratoireDTO) {
        LaboratoireDTO createdLaboratoireDTO =    laboratoireService.createLaboratoire(laboratoireDTO);
        return   ResponseEntity.status(HttpStatus.CREATED).body(createdLaboratoireDTO);
    }
}