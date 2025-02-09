package com.estf.edoctorat.services;

import com.estf.edoctorat.dto.CandidatPostulerDTO;
import com.estf.edoctorat.dto.Result;
import com.estf.edoctorat.dto.SujetDTO;
import com.estf.edoctorat.mappers.CandidatPostulerMapper;
import com.estf.edoctorat.models.CandidatPostuler;
import com.estf.edoctorat.models.Sujet;
import com.estf.edoctorat.repositories.CandidatPostulerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidatPostulerService {

    @Autowired
    private CandidatPostulerRepository candidatPostulerRepository;

    /*@Autowired
    private CandidatPostulerMapper candidatPostulerMapper;*/

    private final CandidatPostulerMapper candidatPostulerMapper = CandidatPostulerMapper.INSTANCE;


    public List<CandidatPostulerDTO> getCandidatPostulers() {
        return candidatPostulerRepository.findAll().stream()
                .map(candidatPostulerMapper::candidatPostulerToCandidatPostulerDTO)
                .collect(Collectors.toList());
    }

    public  List<CandidatPostulerDTO> getCandidatPostulersByCandidatId(Long candidatId) {
        return candidatPostulerRepository.findByCandidatId(candidatId).stream()
                .map(candidatPostulerMapper::candidatPostulerToCandidatPostulerDTO)
                .collect(Collectors.toList());
    }
    public CandidatPostulerDTO createCandidatPostuler(CandidatPostulerDTO candidatPostulerDTO){
        CandidatPostuler candidatPostuler = candidatPostulerMapper.candidatPostulerDTOToCandidatPostuler(candidatPostulerDTO);
        return   candidatPostulerMapper.candidatPostulerToCandidatPostulerDTO(candidatPostulerRepository.save(candidatPostuler));

    }

    public CandidatPostulerDTO getCandidatPostuler(Long id) {
        Optional<CandidatPostuler> candidatPostuler =  candidatPostulerRepository.findById(id);
        return candidatPostuler.map(candidatPostulerMapper::candidatPostulerToCandidatPostulerDTO).orElse(null);
    }
    public boolean deleteCandidatPostuler(Long id) {
        if(candidatPostulerRepository.existsById(id))
        {candidatPostulerRepository.deleteById(id);
            return true;
        }
        else return false;
    }
    public CandidatPostulerDTO updateCandidatPostuler(Long id, CandidatPostulerDTO candidatPostulerDTO) {
        Optional<CandidatPostuler> existingCandidatPostuler = candidatPostulerRepository.findById(id);
        if (existingCandidatPostuler.isPresent()) {
            CandidatPostuler candidatPostuler =   candidatPostulerMapper.candidatPostulerDTOToCandidatPostuler(candidatPostulerDTO);
            candidatPostuler.setId(existingCandidatPostuler.get().getId());
            return candidatPostulerMapper.candidatPostulerToCandidatPostulerDTO(candidatPostulerRepository.save(candidatPostuler));
        } else {
            return null; // Or throw exception
        }
    }

    public Result<CandidatPostulerDTO> getProfesseurCandidats() {
        List<CandidatPostuler> candidats = candidatPostulerRepository.findAll();
        List<CandidatPostulerDTO> candidatsDTOs = candidats.stream().map(candidatPostulerMapper::candidatPostulerToCandidatPostulerDTO).collect(Collectors.toList());
        // Specify Result<SujetDTO> explicitly
        Result<CandidatPostulerDTO> result = new Result<>(candidatsDTOs, candidatsDTOs.size()); // Using size for total count
        System.out.println(result);
        return result;
    }
}