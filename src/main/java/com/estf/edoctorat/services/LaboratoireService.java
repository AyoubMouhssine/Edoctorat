package com.estf.edoctorat.services;
import com.estf.edoctorat.dto.LaboratoireDTO;
import com.estf.edoctorat.mappers.LaboratoireMapper;
import com.estf.edoctorat.models.Laboratoire;
import com.estf.edoctorat.repositories.LaboratoireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LaboratoireService {
    @Autowired
    private LaboratoireRepository laboratoireRepository;
    private final LaboratoireMapper laboratoireMapper = LaboratoireMapper.INSTANCE;

    public List<LaboratoireDTO> getLaboratoires() {
        return laboratoireRepository.findAll().stream()
                .map(laboratoireMapper::laboratoireToLaboratoireDTO)
                .collect(Collectors.toList());
    }
    public LaboratoireDTO getLaboratoire(Long id) {
        Optional<Laboratoire> laboratoire = laboratoireRepository.findById(id);
        return laboratoire.map(laboratoireMapper::laboratoireToLaboratoireDTO).orElse(null);
    }

    public LaboratoireDTO createLaboratoire(LaboratoireDTO laboratoireDTO){
        Laboratoire laboratoire =   laboratoireMapper.laboratoireDTOToLaboratoire(laboratoireDTO);
        return   laboratoireMapper.laboratoireToLaboratoireDTO(laboratoireRepository.save(laboratoire));
    }
}