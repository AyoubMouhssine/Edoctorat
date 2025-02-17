package com.estf.edoctorat.services;

import com.estf.edoctorat.dto.CandidatPostulerDTO;
import com.estf.edoctorat.models.CandidatPostuler;
import com.estf.edoctorat.models.Candidat;
import com.estf.edoctorat.models.Sujet;
import com.estf.edoctorat.repositories.CandidatPostulerRepository;
import com.estf.edoctorat.repositories.CandidatRepository;
import com.estf.edoctorat.repositories.SujetRepository;
import com.estf.edoctorat.mappers.CandidatPostulerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CandidatPostulerService {

    @Autowired
    private CandidatPostulerRepository candidatPostulerRepository;

    @Autowired
    private CandidatRepository candidatRepository;

    @Autowired
    private SujetRepository sujetRepository;

    private final CandidatPostulerMapper mapper = CandidatPostulerMapper.INSTANCE;

    public List<CandidatPostulerDTO> getCandidatPostulers() {
        return candidatPostulerRepository.findAll()
                .stream()
                .map(mapper::candidatPostulerToCandidatPostulerDTO)
                .collect(Collectors.toList());
    }

    public List<CandidatPostulerDTO> getCandidatPostulersByCandidatId(Long candidatId) {
        return candidatPostulerRepository.findByCandidatId(candidatId)
                .stream()
                .map(mapper::candidatPostulerToCandidatPostulerDTO)
                .collect(Collectors.toList());
    }

    public CandidatPostulerDTO getCandidatPostuler(Long id) {
        return candidatPostulerRepository.findById(id)
                .map(mapper::candidatPostulerToCandidatPostulerDTO)
                .orElseThrow(() -> new RuntimeException("CandidatPostuler not found with id: " + id));
    }

    public CandidatPostulerDTO createCandidatPostuler(CandidatPostulerDTO candidatPostulerDTO) {
        CandidatPostuler candidatPostuler = mapper.candidatPostulerDTOToCandidatPostuler(candidatPostulerDTO);

        // Fetch complete Sujet and Candidat entities
        Sujet sujet = sujetRepository.findById(candidatPostulerDTO.getSujetId())
                .orElseThrow(() -> new RuntimeException("Sujet not found with id: " + candidatPostulerDTO.getSujetId()));
        Candidat candidat = candidatRepository.findById(candidatPostulerDTO.getCandidatId())
                .orElseThrow(() -> new RuntimeException("Candidat not found with id: " + candidatPostulerDTO.getCandidatId()));

        candidatPostuler.setSujet(sujet);
        candidatPostuler.setCandidat(candidat);

        CandidatPostuler savedCandidatPostuler = candidatPostulerRepository.save(candidatPostuler);
        return mapper.candidatPostulerToCandidatPostulerDTO(savedCandidatPostuler);
    }

    public boolean deleteCandidatPostuler(Long id) {
        if (candidatPostulerRepository.existsById(id)) {
            candidatPostulerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public CandidatPostulerDTO updateCandidatPostuler(Long id, CandidatPostulerDTO candidatPostulerDTO) {
        return candidatPostulerRepository.findById(id)
                .map(existingPostuler -> {
                    if (candidatPostulerDTO.getPathFile() != null) {
                        existingPostuler.setPathFile(candidatPostulerDTO.getPathFile());
                    }

                    // Update Sujet if provided
                    if (candidatPostulerDTO.getSujetId() != null) {
                        Sujet sujet = sujetRepository.findById(candidatPostulerDTO.getSujetId())
                                .orElseThrow(() -> new RuntimeException("Sujet not found with id: " + candidatPostulerDTO.getSujetId()));
                        existingPostuler.setSujet(sujet);
                    }

                    // Update Candidat if provided
                    if (candidatPostulerDTO.getCandidatId() != null) {
                        Candidat candidat = candidatRepository.findById(candidatPostulerDTO.getCandidatId())
                                .orElseThrow(() -> new RuntimeException("Candidat not found with id: " + candidatPostulerDTO.getCandidatId()));
                        existingPostuler.setCandidat(candidat);
                    }

                    CandidatPostuler updatedPostuler = candidatPostulerRepository.save(existingPostuler);
                    return mapper.candidatPostulerToCandidatPostulerDTO(updatedPostuler);
                })
                .orElseThrow(() -> new RuntimeException("CandidatPostuler not found with id: " + id));
    }
}