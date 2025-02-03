package com.estf.edoctorat.services;
import com.estf.edoctorat.dto.CommissionProfesseurDTO;
import com.estf.edoctorat.mappers.CommissionProfesseurMapper;
import com.estf.edoctorat.models.CommissionProfesseur;
import com.estf.edoctorat.repositories.CommissionProfesseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommissionProfesseurService {
    @Autowired
    private CommissionProfesseurRepository commissionProfesseurRepository;
    private final CommissionProfesseurMapper commissionProfesseurMapper = CommissionProfesseurMapper.INSTANCE;
    public List<CommissionProfesseurDTO> getCommissionProfesseurs() {
        return commissionProfesseurRepository.findAll().stream()
                .map(commissionProfesseurMapper::commissionProfesseurToCommissionProfesseurDTO)
                .collect(Collectors.toList());
    }
    public CommissionProfesseurDTO getCommissionProfesseur(Long id) {
        Optional<CommissionProfesseur> commissionProfesseur = commissionProfesseurRepository.findById(id);
        return commissionProfesseur.map(commissionProfesseurMapper::commissionProfesseurToCommissionProfesseurDTO).orElse(null);
    }

    public CommissionProfesseurDTO createCommissionProfesseur(CommissionProfesseurDTO commissionProfesseurDTO){
        CommissionProfesseur commissionProfesseur =  commissionProfesseurMapper.commissionProfesseurDTOToCommissionProfesseur(commissionProfesseurDTO);
        return    commissionProfesseurMapper.commissionProfesseurToCommissionProfesseurDTO(commissionProfesseurRepository.save(commissionProfesseur));
    }
    public boolean deleteCommissionProfesseur(Long id) {
        if(commissionProfesseurRepository.existsById(id)){
            commissionProfesseurRepository.deleteById(id);
            return true;
        }
        else return false;
    }
}