package com.estf.edoctorat.services;
import com.estf.edoctorat.dto.ExaminerDTO;
import com.estf.edoctorat.mappers.ExaminerMapper;
import com.estf.edoctorat.models.Examiner;
import com.estf.edoctorat.repositories.ExaminerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExaminerService {

    @Autowired
    private ExaminerRepository examinerRepository;
    private final ExaminerMapper examinerMapper = ExaminerMapper.INSTANCE;

    public List<ExaminerDTO> getExaminers() {
        return examinerRepository.findAll().stream()
                .map(examinerMapper::examinerToExaminerDTO)
                .collect(Collectors.toList());
    }

    public ExaminerDTO getExaminer(Long id) {
        Optional<Examiner> examiner = examinerRepository.findById(id);
        return examiner.map(examinerMapper::examinerToExaminerDTO).orElse(null);
    }
    public ExaminerDTO createExaminer(ExaminerDTO examinerDTO){
        Examiner examiner =  examinerMapper.examinerDTOToExaminer(examinerDTO);
        return   examinerMapper.examinerToExaminerDTO(examinerRepository.save(examiner));
    }
}