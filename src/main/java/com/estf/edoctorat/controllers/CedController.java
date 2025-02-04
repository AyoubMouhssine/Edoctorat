package com.estf.edoctorat.controllers;
import com.estf.edoctorat.dto.CedDTO;
import com.estf.edoctorat.services.CedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CedController {
    @Autowired
    private CedService cedService;
    @GetMapping("/ced")
    public ResponseEntity<List<CedDTO>> getCeds() {
        return ResponseEntity.ok(cedService.getCeds());
    }
    @GetMapping("/ced/{id}")
    public ResponseEntity<CedDTO> getCed(@PathVariable Long id) {
        CedDTO cedDTO =  cedService.getCed(id);
        return cedDTO != null ? ResponseEntity.ok(cedDTO) : ResponseEntity.notFound().build();
    }
    @PostMapping("/ced")
    public ResponseEntity<CedDTO> createCed(@RequestBody CedDTO cedDTO) {
        CedDTO createdCedDTO = cedService.createCed(cedDTO);
        return   ResponseEntity.status(HttpStatus.CREATED).body(createdCedDTO);
    }
}