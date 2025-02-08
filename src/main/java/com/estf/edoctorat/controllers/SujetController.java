package com.estf.edoctorat.controllers;

import com.estf.edoctorat.dto.SujetDTO;
import com.estf.edoctorat.services.SujetService;
import com.estf.edoctorat.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SujetController {

    @Autowired
    private SujetService sujetService;

    @GetMapping("/sujets/")
    public ResponseEntity<Result<SujetDTO>> getAllSujets() {
        // Returns Result<SujetDTO> instead of just List<SujetDTO>
        Result<SujetDTO> result = sujetService.getSujets();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/sujetslabo/")
    public ResponseEntity<Result<SujetDTO>> getSujets(@RequestParam(defaultValue = "50") int limit,
                                                      @RequestParam(defaultValue = "0") int offset) {
        // Returns Result<SujetDTO> instead of just List<SujetDTO>
        Result<SujetDTO> result = sujetService.getSujets(limit, offset);
        return ResponseEntity.ok(result);
    }
}
