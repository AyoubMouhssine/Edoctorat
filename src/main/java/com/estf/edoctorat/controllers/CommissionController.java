package com.estf.edoctorat.controllers;
import com.estf.edoctorat.dto.CommissionDTO;
import com.estf.edoctorat.services.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommissionController {
    @Autowired
    private CommissionService commissionService;
    @GetMapping("/commission")
    public ResponseEntity<List<CommissionDTO>> getCommissions() {
        return ResponseEntity.ok(commissionService.getCommissions());
    }
    @GetMapping("/commission/{id}")
    public ResponseEntity<CommissionDTO> getCommission(@PathVariable Long id) {
        CommissionDTO commissionDTO = commissionService.getCommission(id);
        return commissionDTO != null ? ResponseEntity.ok(commissionDTO) : ResponseEntity.notFound().build();
    }
    @PostMapping("/commission")
    public ResponseEntity<CommissionDTO> createCommission(@RequestBody CommissionDTO commissionDTO) {
        CommissionDTO createdCommissionDTO = commissionService.createCommission(commissionDTO);
        return   ResponseEntity.status(HttpStatus.CREATED).body(createdCommissionDTO);
    }
    @DeleteMapping("/commission/{id}")
    public ResponseEntity<Void> deleteCommission(@PathVariable Long id) {
        boolean deleted = commissionService.deleteCommission(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}