package com.estf.edoctorat.services;

import com.estf.edoctorat.dto.CandidatDTO;
import com.estf.edoctorat.mappers.CandidatMapper;
import com.estf.edoctorat.models.Candidat;
import com.estf.edoctorat.models.User;
import com.estf.edoctorat.repositories.CandidatRepository;
import com.estf.edoctorat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CandidatService {

    @Autowired
    private CandidatRepository candidatRepository;
    @Autowired
    private UserRepository userRepository;

    private final CandidatMapper candidatMapper = CandidatMapper.INSTANCE;


    public CandidatDTO getCandidatById(Long id) {
        Optional<Candidat> candidat =  candidatRepository.findById(id);
        return candidat.map(candidatMapper::candidatToCandidatDTO).orElse(null);
    }

    /*public CandidatDTO getCandidatByUserId(Long id) {
        Optional<Candidat> candidat = candidatRepository.findByUserId(id);
        return candidat.map(candidatMapper::candidatToCandidatDTO).orElse(null);
    }*/

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


    public CandidatDTO getCandidatByUserId(Long userId) {
        Optional<Candidat> candidat = candidatRepository.findByUserId(userId);
        return candidat.map(candidatMapper::candidatToCandidatDTO).orElse(null);
    }

    @Transactional
    public CandidatDTO updateCandidatInfo(Long userId, CandidatDTO candidatDTO) {
        Optional<Candidat> existingCandidatOpt = candidatRepository.findByUserId(userId);
        if (existingCandidatOpt.isPresent()) {
            Candidat existingCandidat = existingCandidatOpt.get();

            // Update Candidat fields
            existingCandidat.setCin(candidatDTO.getCin());
            existingCandidat.setCne(candidatDTO.getCne());
            existingCandidat.setAdresse(candidatDTO.getAdresse());
            existingCandidat.setSexe(candidatDTO.getSexe());
            existingCandidat.setVilleDeNaissance(candidatDTO.getVilleDeNaissance());
            existingCandidat.setVille(candidatDTO.getVille());
            existingCandidat.setDateDeNaissance(candidatDTO.getDateDeNaissance());
            existingCandidat.setTypeDeHandiCape(candidatDTO.getTypeDeHandiCape());
            existingCandidat.setAcademie(candidatDTO.getAcademie());
            existingCandidat.setTelCandidat(candidatDTO.getTelCandidat());
            existingCandidat.setSituationFamiliale(candidatDTO.getSituationFamiliale());
            existingCandidat.setFonctionnaire(candidatDTO.isFonctionnaire());
            existingCandidat.setPathPhoto(candidatDTO.getPathPhoto());

            // Update User fields
            User user = existingCandidat.getUser();
            user.setFirstName(candidatDTO.getPrenom()); // Update prenom
            user.setLastName(candidatDTO.getNom());     // Update nom
            user.setEmail(candidatDTO.getEmail());      // Update email
            userRepository.save(user);

            // Save updated Candidat
            Candidat savedCandidat = candidatRepository.save(existingCandidat);
            return candidatMapper.candidatToCandidatDTO(savedCandidat);
        } else {
            return null; // Or throw an exception
        }
    }

}