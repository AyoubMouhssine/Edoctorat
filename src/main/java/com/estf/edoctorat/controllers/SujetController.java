package com.estf.edoctorat.controllers;

import com.estf.edoctorat.dto.SujetDTO;
import com.estf.edoctorat.services.SujetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SujetController {

    @Autowired
    private SujetService sujetService;

    @GetMapping(value = {"/sujets", "/sujets/"})
    public ResponseEntity<List<SujetDTO>> getSujets() {
        return ResponseEntity.ok(sujetService.getSujets());
    }

    @GetMapping(value = {"/sujets/{id}", "/sujets/{id}/"})
    public ResponseEntity<SujetDTO> getSujet(@PathVariable Long id) {
        SujetDTO sujetDTO = sujetService.getSujet(id);
        return sujetDTO != null ? ResponseEntity.ok(sujetDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping(value = {"/sujets", "/sujets/"})
    public ResponseEntity<SujetDTO> createSujet(@RequestBody SujetDTO sujetDTO) {
        SujetDTO createdSujetDTO = sujetService.createSujet(sujetDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSujetDTO);
    }

    @PutMapping(value = {"/sujets/{id}", "/sujets/{id}/"})
    public ResponseEntity<SujetDTO> updateSujet(@PathVariable Long id, @RequestBody SujetDTO sujetDTO) {
        SujetDTO updatedSujetDTO = sujetService.updateSujet(id, sujetDTO);
        return updatedSujetDTO != null ? ResponseEntity.ok(updatedSujetDTO) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = {"/sujets/{id}", "/sujets/{id}/"})
    public ResponseEntity<Void> deleteSujet(@PathVariable Long id) {
        boolean deleted = sujetService.deleteSujet(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}