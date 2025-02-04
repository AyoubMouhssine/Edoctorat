// src/main/java/com/estf/edoctorat/services/DiplomeService.java
package com.estf.edoctorat.services;

import com.estf.edoctorat.dto.DiplomeDTO;
import com.estf.edoctorat.mappers.DiplomeMapper;
import com.estf.edoctorat.models.Diplome;
import com.estf.edoctorat.repositories.DiplomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiplomeService {

    @Autowired
    private DiplomeRepository diplomeRepository;
    private final DiplomeMapper diplomeMapper = DiplomeMapper.INSTANCE;

    public List<DiplomeDTO> getDiplomes() {
        return diplomeRepository.findAll().stream()
                .map(diplomeMapper::diplomeToDiplomeDTO)
                .collect(Collectors.toList());
    }
    public List<DiplomeDTO> getDiplomesByCandidatId(Long candidatId){
        return   diplomeRepository.findByCandidatId(candidatId).stream()
                .map(diplomeMapper::diplomeToDiplomeDTO)
                .collect(Collectors.toList());
    }
    public DiplomeDTO getDiplome(Long id) {
        Optional<Diplome> diplome =  diplomeRepository.findById(id);
        return diplome.map(diplomeMapper::diplomeToDiplomeDTO).orElse(null);
    }
    public DiplomeDTO createDiplome(DiplomeDTO diplomeDTO){
        Diplome diplome = diplomeMapper.diplomeDTOToDiplome(diplomeDTO);
        return   diplomeMapper.diplomeToDiplomeDTO(diplomeRepository.save(diplome));
    }
    public boolean deleteDiplome(Long id) {
        if(diplomeRepository.existsById(id)){
            diplomeRepository.deleteById(id);
            return true;
        }
        else return false;
    }

    public DiplomeDTO updateDiplome(Long id, DiplomeDTO diplomeDTO) {
        Optional<Diplome> existingDiplome = diplomeRepository.findById(id);
        if (existingDiplome.isPresent()) {
            Diplome diplome = diplomeMapper.diplomeDTOToDiplome(diplomeDTO);
            diplome.setId(existingDiplome.get().getId());
            return  diplomeMapper.diplomeToDiplomeDTO(diplomeRepository.save(diplome));
        } else {
            return null; // Or throw exception
        }
    }
}