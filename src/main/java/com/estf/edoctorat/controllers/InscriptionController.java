package com.estf.edoctorat.controllers;
import com.estf.edoctorat.dto.InscriptionDTO;
import com.estf.edoctorat.services.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
public class InscriptionController {

    @Autowired
    private InscriptionService inscriptionService;
    @GetMapping("/add-inscription/{id}")
    public ResponseEntity<InscriptionDTO> getInscriptionById(@PathVariable Long id) {
        InscriptionDTO inscriptionDTO =  inscriptionService.getInscription(id);
        return inscriptionDTO != null ? ResponseEntity.ok(inscriptionDTO) : ResponseEntity.notFound().build();
    }
    @PostMapping("/add-inscription")
    public ResponseEntity<InscriptionDTO> createInscription(@RequestBody InscriptionDTO inscriptionDTO) {
        InscriptionDTO createdInscriptionDTO = inscriptionService.createInscription(inscriptionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInscriptionDTO);
    }
}