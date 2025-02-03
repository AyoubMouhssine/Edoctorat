package com.estf.edoctorat.services;
import com.estf.edoctorat.dto.EtablissementDTO;
import com.estf.edoctorat.mappers.EtablissementMapper;
import com.estf.edoctorat.models.Etablissement;
import com.estf.edoctorat.repositories.EtablissementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EtablissementService {
    @Autowired
    private EtablissementRepository etablissementRepository;
    private final EtablissementMapper etablissementMapper = EtablissementMapper.INSTANCE;

    public List<EtablissementDTO> getEtablissements() {
        return etablissementRepository.findAll().stream()
                .map(etablissementMapper::etablissementToEtablissementDTO)
                .collect(Collectors.toList());
    }
    public EtablissementDTO getEtablissement(Long id) {
        Optional<Etablissement> etablissement = etablissementRepository.findById(id);
        return etablissement.map(etablissementMapper::etablissementToEtablissementDTO).orElse(null);
    }
    public EtablissementDTO createEtablissement(EtablissementDTO etablissementDTO){
        Etablissement etablissement =  etablissementMapper.etablissementDTOToEtablissement(etablissementDTO);
        return   etablissementMapper.etablissementToEtablissementDTO(etablissementRepository.save(etablissement));
    }
}