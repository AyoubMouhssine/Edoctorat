package com.estf.edoctorat.services;
import com.estf.edoctorat.dto.ProfesseurLaboratoireDTO;
import com.estf.edoctorat.mappers.ProfesseurLaboratoireMapper;
import com.estf.edoctorat.models.ProfesseurLaboratoire;
import com.estf.edoctorat.repositories.ProfesseurLaboratoireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfesseurLaboratoireService {
    @Autowired
    private ProfesseurLaboratoireRepository professeurLaboratoireRepository;
    private final ProfesseurLaboratoireMapper professeurLaboratoireMapper = ProfesseurLaboratoireMapper.INSTANCE;
    public List<ProfesseurLaboratoireDTO> getProfesseurLaboratoires() {
        return professeurLaboratoireRepository.findAll().stream()
                .map(professeurLaboratoireMapper::professeurLaboratoireToProfesseurLaboratoireDTO)
                .collect(Collectors.toList());
    }
    public ProfesseurLaboratoireDTO getProfesseurLaboratoire(Long id) {
        Optional<ProfesseurLaboratoire> professeurLaboratoire = professeurLaboratoireRepository.findById(id);
        return professeurLaboratoire.map(professeurLaboratoireMapper::professeurLaboratoireToProfesseurLaboratoireDTO).orElse(null);
    }
    public ProfesseurLaboratoireDTO createProfesseurLaboratoire(ProfesseurLaboratoireDTO professeurLaboratoireDTO){
        ProfesseurLaboratoire professeurLaboratoire =  professeurLaboratoireMapper.professeurLaboratoireDTOToProfesseurLaboratoire(professeurLaboratoireDTO);
        return   professeurLaboratoireMapper.professeurLaboratoireToProfesseurLaboratoireDTO(professeurLaboratoireRepository.save(professeurLaboratoire));
    }
}