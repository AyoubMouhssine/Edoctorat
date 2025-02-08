package com.estf.edoctorat.services;

import com.estf.edoctorat.dto.SujetDTO;
import com.estf.edoctorat.mappers.SujetMapper;
import com.estf.edoctorat.models.Sujet;
import com.estf.edoctorat.repositories.SujetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SujetService {

    @Autowired
    private SujetRepository sujetRepository;

    private final SujetMapper sujetMapper = SujetMapper.INSTANCE;

    public List<SujetDTO> getSujets() {
        return sujetRepository.findAll().stream()
                .map(sujetMapper::sujetToSujetDTO)
                .collect(Collectors.toList());
    }

    public SujetDTO getSujet(Long id) {
        Optional<Sujet> sujet = sujetRepository.findById(id);
        return sujet.map(sujetMapper::sujetToSujetDTO).orElse(null);
    }

    public SujetDTO createSujet(SujetDTO sujetDTO) {
        Sujet sujet = sujetMapper.sujetDTOToSujet(sujetDTO);
        return sujetMapper.sujetToSujetDTO(sujetRepository.save(sujet));
    }

    public SujetDTO updateSujet(Long id, SujetDTO sujetDTO) {
        Optional<Sujet> existingSujet = sujetRepository.findById(id);
        if (existingSujet.isPresent()) {
            Sujet sujet = sujetMapper.sujetDTOToSujet(sujetDTO);
            sujet.setId(existingSujet.get().getId());
            return sujetMapper.sujetToSujetDTO(sujetRepository.save(sujet));
        } else {
            return null;
        }
    }

    public boolean deleteSujet(Long id) {
        if (sujetRepository.existsById(id)) {
            sujetRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}