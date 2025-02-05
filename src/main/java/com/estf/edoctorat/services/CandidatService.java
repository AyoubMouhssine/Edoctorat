package com.estf.edoctorat.services;

import com.estf.edoctorat.dto.CandidatDTO;
import com.estf.edoctorat.mappers.CandidatMapper;
import com.estf.edoctorat.models.Candidat;
import com.estf.edoctorat.repositories.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CandidatService {

    @Autowired
    private CandidatRepository candidatRepository;

    private final CandidatMapper candidatMapper = CandidatMapper.INSTANCE;


    public CandidatDTO getCandidatById(Long id) {
        Optional<Candidat> candidat =  candidatRepository.findById(id);
        return candidat.map(candidatMapper::candidatToCandidatDTO).orElse(null);
    }

    public CandidatDTO getCandidatByUserId(Long id) {
        Optional<Candidat> candidat = candidatRepository.findByUserId(id);
        return candidat.map(candidatMapper::candidatToCandidatDTO).orElse(null);
    }

    public CandidatDTO updateCandidat(Long id, CandidatDTO candidatDTO) {
        Optional<Candidat> existingCandidat = candidatRepository.findById(id);
        if (existingCandidat.isPresent()) {
            Candidat candidat =   candidatMapper.candidatDTOToCandidat(candidatDTO);
            candidat.setId(existingCandidat.get().getId());
            return candidatMapper.candidatToCandidatDTO(candidatRepository.save(candidat));
        } else {
            return null; // Or throw exception
        }
    }
    public CandidatDTO createCandidat(CandidatDTO candidatDTO){
        Candidat candidat = candidatMapper.candidatDTOToCandidat(candidatDTO);
        return candidatMapper.candidatToCandidatDTO(candidatRepository.save(candidat));
    }

    public Candidat createCandidat(Candidat candidat) {
        return candidatRepository.save(candidat);
    }

}