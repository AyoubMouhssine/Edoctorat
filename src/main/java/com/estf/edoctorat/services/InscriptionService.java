// src/main/java/com/estf/edoctorat/services/InscriptionService.java
package com.estf.edoctorat.services;
import com.estf.edoctorat.dto.InscriptionDTO;
import com.estf.edoctorat.mappers.InscriptionMapper;
import com.estf.edoctorat.models.Inscription;
import com.estf.edoctorat.repositories.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class InscriptionService {
    @Autowired
    private InscriptionRepository inscriptionRepository;
    private final InscriptionMapper inscriptionMapper = InscriptionMapper.INSTANCE;

    public InscriptionDTO getInscription(Long id) {
        Optional<Inscription> inscription = inscriptionRepository.findById(id);
        return inscription.map(inscriptionMapper::inscriptionToInscriptionDTO).orElse(null);
    }

    public InscriptionDTO createInscription(InscriptionDTO inscriptionDTO){
        Inscription inscription = inscriptionMapper.inscriptionDTOToInscription(inscriptionDTO);
        return  inscriptionMapper.inscriptionToInscriptionDTO(inscriptionRepository.save(inscription));
    }
}