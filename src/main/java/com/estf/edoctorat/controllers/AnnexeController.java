package com.estf.edoctorat.controllers;
import com.estf.edoctorat.dto.AnnexeDTO;
import com.estf.edoctorat.services.AnnexeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnnexeController {
    @Autowired
    private AnnexeService annexeService;
    @GetMapping("/annexe")
    public ResponseEntity<List<AnnexeDTO>> getAnnexes() {
        return ResponseEntity.ok(annexeService.getAnnexes());
    }
    @GetMapping("/annexe/{id}")
    public ResponseEntity<AnnexeDTO> getAnnexe(@PathVariable Long id) {
        AnnexeDTO annexeDTO =   annexeService.getAnnexe(id);
        return annexeDTO != null ? ResponseEntity.ok(annexeDTO) : ResponseEntity.notFound().build();
    }
    @PostMapping("/annexe")
    public ResponseEntity<AnnexeDTO> createAnnexe(@RequestBody AnnexeDTO annexeDTO) {
        AnnexeDTO createdAnnexeDTO = annexeService.createAnnexe(annexeDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(createdAnnexeDTO);
    }
    @PutMapping("/annexe/{id}")
    public ResponseEntity<AnnexeDTO> updateAnnexe(@PathVariable Long id, @RequestBody AnnexeDTO annexeDTO) {
        AnnexeDTO updatedAnnexeDTO = annexeService.updateAnnexe(id, annexeDTO);
        return updatedAnnexeDTO != null ? ResponseEntity.ok(updatedAnnexeDTO) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/annexe/{id}")
    public ResponseEntity<Void> deleteAnnexe(@PathVariable Long id) {
        boolean deleted = annexeService.deleteAnnexe(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}