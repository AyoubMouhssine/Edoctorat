package com.estf.edoctorat.controllers;
import com.estf.edoctorat.dto.DirecteurPoleCalendrierDTO;
import com.estf.edoctorat.services.DirecteurPoleCalendrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DirecteurPoleCalendrierController {
    @Autowired
    private DirecteurPoleCalendrierService directeurPoleCalendrierService;
    @GetMapping("/get-calendrier")
    public ResponseEntity<List<DirecteurPoleCalendrierDTO>> getDirecteurPoleCalendriers() {
        return ResponseEntity.ok(directeurPoleCalendrierService.getDirecteurPoleCalendriers());
    }
    @GetMapping("/get-calendrier/{id}")
    public ResponseEntity<DirecteurPoleCalendrierDTO> getDirecteurPoleCalendrier(@PathVariable Long id) {
        DirecteurPoleCalendrierDTO directeurPoleCalendrierDTO =   directeurPoleCalendrierService.getDirecteurPoleCalendrier(id);
        return  directeurPoleCalendrierDTO != null ? ResponseEntity.ok(directeurPoleCalendrierDTO) : ResponseEntity.notFound().build();
    }
    @PostMapping("/get-calendrier")
    public ResponseEntity<DirecteurPoleCalendrierDTO> createDirecteurPoleCalendrier(@RequestBody DirecteurPoleCalendrierDTO directeurPoleCalendrierDTO) {
        DirecteurPoleCalendrierDTO createdDirecteurPoleCalendrierDTO =  directeurPoleCalendrierService.createDirecteurPoleCalendrier(directeurPoleCalendrierDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(createdDirecteurPoleCalendrierDTO);
    }
    @PatchMapping("/update-canlendrier/{id}")
    public ResponseEntity<DirecteurPoleCalendrierDTO> updateDirecteurPoleCalendrier(@PathVariable Long id, @RequestBody DirecteurPoleCalendrierDTO directeurPoleCalendrierDTO) {
        DirecteurPoleCalendrierDTO updatedDirecteurPoleCalendrierDTO =  directeurPoleCalendrierService.updateDirecteurPoleCalendrier(id, directeurPoleCalendrierDTO);
        return  updatedDirecteurPoleCalendrierDTO != null ? ResponseEntity.ok(updatedDirecteurPoleCalendrierDTO) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/get-calendrier/{id}")
    public ResponseEntity<Void> deleteDirecteurPoleCalendrier(@PathVariable Long id) {
        boolean deleted = directeurPoleCalendrierService.deleteDirecteurPoleCalendrier(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}