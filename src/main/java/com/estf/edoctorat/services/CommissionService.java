package com.estf.edoctorat.services;
import com.estf.edoctorat.dto.CommissionDTO;
import com.estf.edoctorat.mappers.CommissionMapper;
import com.estf.edoctorat.models.Commission;
import com.estf.edoctorat.repositories.CommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommissionService {
    @Autowired
    private CommissionRepository commissionRepository;
    private final CommissionMapper commissionMapper = CommissionMapper.INSTANCE;

    public List<CommissionDTO> getCommissions() {
        return commissionRepository.findAll().stream()
                .map(commissionMapper::commissionToCommissionDTO)
                .collect(Collectors.toList());
    }
    public CommissionDTO getCommission(Long id) {
        Optional<Commission> commission = commissionRepository.findById(id);
        return commission.map(commissionMapper::commissionToCommissionDTO).orElse(null);
    }
    public CommissionDTO createCommission(CommissionDTO commissionDTO){
        Commission commission =  commissionMapper.commissionDTOToCommission(commissionDTO);
        return    commissionMapper.commissionToCommissionDTO(commissionRepository.save(commission));
    }
    public boolean deleteCommission(Long id) {
        if(commissionRepository.existsById(id)){
            commissionRepository.deleteById(id);
            return true;
        }
        else return false;
    }
}