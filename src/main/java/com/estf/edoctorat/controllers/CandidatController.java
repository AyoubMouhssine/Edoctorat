package com.estf.edoctorat.controllers;
import com.estf.edoctorat.dto.CandidatDTO;
import com.estf.edoctorat.services.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CandidatController {

    @Autowired
    private CandidatService candidatService;


    @GetMapping("/candidat-info")
    public ResponseEntity<CandidatDTO> getCandidatInfo(@RequestParam Long userId) {
        CandidatDTO candidatDTO =  candidatService.getCandidatByUserId(userId);
        return candidatDTO != null ? ResponseEntity.ok(candidatDTO) : ResponseEntity.notFound().build();
    }
    @GetMapping("/get-candidat-profile/{id}")
    public ResponseEntity<CandidatDTO> getCandidatProfile(@PathVariable Long id) {
        CandidatDTO candidatDTO =  candidatService.getCandidatById(id);
        return candidatDTO != null ? ResponseEntity.ok(candidatDTO) : ResponseEntity.notFound().build();
    }


    @PutMapping("/candidat-info")
    public ResponseEntity<CandidatDTO> updateCandidatInfo(@RequestParam Long id, @RequestBody CandidatDTO candidatDTO) {
        CandidatDTO updatedCandidatDTO = candidatService.updateCandidat(id,candidatDTO);
        return updatedCandidatDTO != null ? ResponseEntity.ok(updatedCandidatDTO) : ResponseEntity.notFound().build();
    }
    @PostMapping("/candidat-info")
    public ResponseEntity<CandidatDTO> createCandidat(@RequestBody CandidatDTO candidatDTO) {
        CandidatDTO createdCandidatDTO = candidatService.createCandidat(candidatDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(createdCandidatDTO);
    }
}