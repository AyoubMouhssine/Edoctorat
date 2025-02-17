package com.estf.edoctorat.services;

import com.estf.edoctorat.dto.DiplomeDTO;
import com.estf.edoctorat.dto.Result;
import com.estf.edoctorat.mappers.DiplomeMapper;
import com.estf.edoctorat.models.Annexe;
import com.estf.edoctorat.models.Candidat;
import com.estf.edoctorat.models.Diplome;
import com.estf.edoctorat.repositories.AnnexeRepository;
import com.estf.edoctorat.repositories.CandidatRepository;
import com.estf.edoctorat.repositories.DiplomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiplomeService {

    @Autowired
    private DiplomeRepository diplomeRepository;
    @Autowired
    private CandidatRepository candidatRepository;
    @Autowired
    private AnnexeRepository annexeRepository;

    private final DiplomeMapper diplomeMapper = DiplomeMapper.INSTANCE;

    public List<DiplomeDTO> getDiplomes() {
        return diplomeRepository.findAll().stream()
                .map(diplomeMapper::diplomeToDiplomeDTO)
                .collect(Collectors.toList());
    }
    public List<DiplomeDTO> getDiplomesByCandidatId(Long candidatId){
        Candidat candidat = candidatRepository.findByUserId(candidatId)
                .orElseThrow(() -> new RuntimeException("Candidat not found for user ID: " + candidatId));

        List<Diplome> diplomes = diplomeRepository.findByCandidatId(candidat.getId());

        // Convert to DTOs
        return diplomes.stream()
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
    public Result<DiplomeDTO> addDiplome(DiplomeDTO diplomeDTO, List<Annexe> annexes, Long userId) {
        // Find the candidate by user ID
        Candidat candidat = candidatRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Candidat not found for user ID: " + userId));

        // Create and save the Diplome
        Diplome diplome = DiplomeMapper.INSTANCE.diplomeDTOToDiplome(diplomeDTO);
        diplome.setCandidat(candidat);
        diplome = diplomeRepository.save(diplome);

        // Save the annexes and associate them with the Diplome
        List<Annexe> savedAnnexes = new ArrayList<>();
        for (Annexe annexe : annexes) {
            annexe.setDiplome(diplome);
            savedAnnexes.add(annexeRepository.save(annexe));
        }

        // Set saved annexes to the Diplome
        diplome.setAnnexes(savedAnnexes);

        // Convert Diplome to DiplomeDTO
        DiplomeDTO resultDTO = DiplomeMapper.INSTANCE.diplomeToDiplomeDTO(diplome);

        // Create and populate the Result object
        Result<DiplomeDTO> result = new Result<>();
        result.setCount(1);  // Since we're saving a single Diplome
        result.setNext(null);  // Can be used for pagination if needed
        result.setPrevious(null);  // Can be used for pagination if needed
        result.setResults(Collections.singletonList(resultDTO));  // Add the saved DiplomeDTO to the results list

        return result;
    }


}