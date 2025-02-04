package com.estf.edoctorat.services;
import com.estf.edoctorat.dto.ProfesseurDTO;
import com.estf.edoctorat.mappers.ProfesseurMapper;
import com.estf.edoctorat.models.Professeur;
import com.estf.edoctorat.repositories.ProfesseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfesseurService {
    @Autowired
    private ProfesseurRepository professeurRepository;
    private final ProfesseurMapper professeurMapper = ProfesseurMapper.INSTANCE;

    public List<ProfesseurDTO> getProfesseurs() {
        return professeurRepository.findAll().stream()
                .map(professeurMapper::professeurToProfesseurDTO)
                .collect(Collectors.toList());
    }
    public ProfesseurDTO getProfesseur(Long id) {
        Optional<Professeur> professeur = professeurRepository.findById(id);
        return professeur.map(professeurMapper::professeurToProfesseurDTO).orElse(null);
    }

    public ProfesseurDTO createProfesseur(ProfesseurDTO professeurDTO){
        Professeur professeur = professeurMapper.professeurDTOToProfesseur(professeurDTO);
        return   professeurMapper.professeurToProfesseurDTO(professeurRepository.save(professeur));
    }
}