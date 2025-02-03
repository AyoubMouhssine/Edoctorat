package com.estf.edoctorat.controllers;
import com.estf.edoctorat.dto.EtablissementDTO;
import com.estf.edoctorat.services.EtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EtablissementController {
    @Autowired
    private EtablissementService etablissementService;
    @GetMapping("/etablissement")
    public ResponseEntity<List<EtablissementDTO>> getEtablissements() {
        return ResponseEntity.ok(etablissementService.getEtablissements());
    }
    @GetMapping("/etablissement/{id}")
    public ResponseEntity<EtablissementDTO> getEtablissement(@PathVariable Long id) {
        EtablissementDTO etablissementDTO =   etablissementService.getEtablissement(id);
        return  etablissementDTO != null ? ResponseEntity.ok(etablissementDTO) : ResponseEntity.notFound().build();
    }
    @PostMapping("/etablissement")
    public ResponseEntity<EtablissementDTO> createEtablissement(@RequestBody EtablissementDTO etablissementDTO) {
        EtablissementDTO createdEtablissementDTO =   etablissementService.createEtablissement(etablissementDTO);
        return    ResponseEntity.status(HttpStatus.CREATED).body(createdEtablissementDTO);
    }
}