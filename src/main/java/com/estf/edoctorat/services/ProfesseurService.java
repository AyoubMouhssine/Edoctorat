package com.estf.edoctorat.services;

import com.estf.edoctorat.dto.ProfesseurDTO;
import com.estf.edoctorat.dto.Result;  // Import the Result class
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

    // Updated method to return Result<ProfesseurDTO>
    public Result<ProfesseurDTO> getProfesseurs() {
        List<Professeur> professeurs = professeurRepository.findAll();
        List<ProfesseurDTO> professeurDTOs = professeurs.stream()
                .map(professeurMapper::professeurToProfesseurDTO)
                .collect(Collectors.toList());
        // Wrap the list in a Result with the total count
        return new Result<>(professeurDTOs, professeurDTOs.size());
    }

    public ProfesseurDTO getProfesseur(Long id) {
        Optional<Professeur> professeur = professeurRepository.findById(id);
        return professeur.map(professeurMapper::professeurToProfesseurDTO).orElse(null);
    }

    public ProfesseurDTO createProfesseur(ProfesseurDTO professeurDTO) {
        Professeur professeur = professeurMapper.professeurDTOToProfesseur(professeurDTO);
        Professeur savedProfesseur = professeurRepository.save(professeur);
        return professeurMapper.professeurToProfesseurDTO(savedProfesseur);
    }
}
