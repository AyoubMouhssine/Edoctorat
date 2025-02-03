package com.estf.edoctorat.controllers;
import com.estf.edoctorat.dto.DiplomeDTO;
import com.estf.edoctorat.services.DiplomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DiplomeController {
    @Autowired
    private DiplomeService diplomeService;

    @GetMapping("/candidat-parcours")
    public ResponseEntity<List<DiplomeDTO>> getDiplomes(@RequestParam(required = false) Long candidatId) {
        List<DiplomeDTO> diplomeDTOS;
        if(candidatId == null){
            diplomeDTOS =  diplomeService.getDiplomes();
        }else{
            diplomeDTOS = diplomeService.getDiplomesByCandidatId(candidatId);
        }
        return  ResponseEntity.ok(diplomeDTOS);
    }
    @GetMapping("/candidat-parcours/{id}")
    public ResponseEntity<DiplomeDTO> getDiplomeById(@PathVariable Long id) {
        DiplomeDTO diplomeDTO =  diplomeService.getDiplome(id);
        return diplomeDTO != null ? ResponseEntity.ok(diplomeDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping("/candidat-parcours")
    public ResponseEntity<DiplomeDTO> createDiplome(@RequestBody DiplomeDTO diplomeDTO) {
        DiplomeDTO createdDiplomeDTO = diplomeService.createDiplome(diplomeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDiplomeDTO);
    }
    @DeleteMapping("/candidat-parcours/{id}")
    public ResponseEntity<Void> deleteDiplome(@PathVariable Long id) {
        boolean deleted = diplomeService.deleteDiplome(id);
        return   deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();

    }
    @PatchMapping("/candidat-parcours/{id}")
    public ResponseEntity<DiplomeDTO> updateDiplome(@PathVariable Long id, @RequestBody DiplomeDTO diplomeDTO) {
        DiplomeDTO updatedDiplomeDTO = diplomeService.updateDiplome(id, diplomeDTO);
        return updatedDiplomeDTO != null ? ResponseEntity.ok(updatedDiplomeDTO) : ResponseEntity.notFound().build();
    }
}