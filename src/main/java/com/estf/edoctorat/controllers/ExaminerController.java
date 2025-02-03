package com.estf.edoctorat.controllers;
import com.estf.edoctorat.dto.ExaminerDTO;
import com.estf.edoctorat.services.ExaminerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExaminerController {

    @Autowired
    private ExaminerService examinerService;
    @GetMapping("/examiner")
    public ResponseEntity<List<ExaminerDTO>> getExaminers() {
        return ResponseEntity.ok(examinerService.getExaminers());
    }
    @GetMapping("/examiner/{id}")
    public ResponseEntity<ExaminerDTO> getExaminer(@PathVariable Long id) {
        ExaminerDTO examinerDTO =  examinerService.getExaminer(id);
        return examinerDTO != null ? ResponseEntity.ok(examinerDTO) : ResponseEntity.notFound().build();
    }
    @PostMapping("/examiner")
    public ResponseEntity<ExaminerDTO> createExaminer(@RequestBody ExaminerDTO examinerDTO) {
        ExaminerDTO createdExaminerDTO = examinerService.createExaminer(examinerDTO);
        return   ResponseEntity.status(HttpStatus.CREATED).body(createdExaminerDTO);
    }
}