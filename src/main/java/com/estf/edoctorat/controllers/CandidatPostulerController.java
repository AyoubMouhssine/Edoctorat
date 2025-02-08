package com.estf.edoctorat.controllers;
import com.estf.edoctorat.dto.CandidatPostulerDTO;
import com.estf.edoctorat.dto.Result;
import com.estf.edoctorat.services.CandidatPostulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CandidatPostulerController {
    @Autowired
    private CandidatPostulerService candidatPostulerService;
    @GetMapping("/candidat-postules")
    public ResponseEntity<List<CandidatPostulerDTO>> getCandidatPostulers(@RequestParam(required = false) Long candidatId) {
        List<CandidatPostulerDTO> candidatPostulerDTOS;
        if(candidatId == null){
            candidatPostulerDTOS = candidatPostulerService.getCandidatPostulers();
        }
        else{
            candidatPostulerDTOS = candidatPostulerService.getCandidatPostulersByCandidatId(candidatId);
        }
        return ResponseEntity.ok(candidatPostulerDTOS);
    }
    @GetMapping("/candidat-postules/{id}")
    public ResponseEntity<CandidatPostulerDTO> getCandidatPostulerById(@PathVariable Long id) {
        CandidatPostulerDTO candidatPostulerDTO =  candidatPostulerService.getCandidatPostuler(id);
        return candidatPostulerDTO != null ? ResponseEntity.ok(candidatPostulerDTO) : ResponseEntity.notFound().build();
    }
    @PostMapping("/candidat-postules")
    public ResponseEntity<CandidatPostulerDTO> createCandidatPostuler(@RequestBody CandidatPostulerDTO candidatPostulerDTO) {
        CandidatPostulerDTO createdCandidatPostulerDTO =   candidatPostulerService.createCandidatPostuler(candidatPostulerDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(createdCandidatPostulerDTO);
    }
    @DeleteMapping("/candidat-postules/{id}")
    public ResponseEntity<Void> deleteCandidatPostuler(@PathVariable Long id) {
        boolean deleted = candidatPostulerService.deleteCandidatPostuler(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PatchMapping("/candidat-postules/{id}")
    public ResponseEntity<CandidatPostulerDTO> updateCandidatPostuler(@PathVariable Long id, @RequestBody CandidatPostulerDTO candidatPostulerDTO) {
        CandidatPostulerDTO updateCandidatPostulerDTO =   candidatPostulerService.updateCandidatPostuler(id,candidatPostulerDTO);
        return  updateCandidatPostulerDTO != null ? ResponseEntity.ok(updateCandidatPostulerDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping("/get-professeur-candidats")
    public ResponseEntity<Result<CandidatPostulerDTO>> getProfesseurCandidats() {
        Result<CandidatPostulerDTO> result = candidatPostulerService.getProfesseurCandidats();
        System.out.println(result);
        return ResponseEntity.ok(result);
    }
}