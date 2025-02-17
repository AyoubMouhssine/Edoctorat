package com.estf.edoctorat.controllers;

import com.estf.edoctorat.dto.CandidatPostulerDTO;
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

    @GetMapping("/candidat-postules/")
    public ResponseEntity<List<CandidatPostulerDTO>> getCandidatPostulers(
            @RequestParam(required = false) Long candidatId) {
        try {
            List<CandidatPostulerDTO> candidatPostulerDTOS;
            if (candidatId == null) {
                candidatPostulerDTOS = candidatPostulerService.getCandidatPostulers();
            } else {
                candidatPostulerDTOS = candidatPostulerService.getCandidatPostulersByCandidatId(candidatId);
            }
            return ResponseEntity.ok(candidatPostulerDTOS);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/candidat-postules/{id}")
    public ResponseEntity<CandidatPostulerDTO> getCandidatPostulerById(@PathVariable Long id) {
        try {
            CandidatPostulerDTO candidatPostulerDTO = candidatPostulerService.getCandidatPostuler(id);
            return ResponseEntity.ok(candidatPostulerDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/candidat-postules/")
    public ResponseEntity<CandidatPostulerDTO> createCandidatPostuler(
            @RequestBody CandidatPostulerDTO candidatPostulerDTO) {
        try {
            CandidatPostulerDTO createdDTO = candidatPostulerService.createCandidatPostuler(candidatPostulerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/candidat-postules/{id}")
    public ResponseEntity<Void> deleteCandidatPostuler(@PathVariable Long id) {
        try {
            boolean deleted = candidatPostulerService.deleteCandidatPostuler(id);
            return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/candidat-postules/{id}/")
    public ResponseEntity<CandidatPostulerDTO> updateCandidatPostuler(
            @PathVariable Long id,
            @RequestBody CandidatPostulerDTO candidatPostulerDTO) {
        try {
            CandidatPostulerDTO updatedDTO = candidatPostulerService.updateCandidatPostuler(id, candidatPostulerDTO);
            return ResponseEntity.ok(updatedDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}