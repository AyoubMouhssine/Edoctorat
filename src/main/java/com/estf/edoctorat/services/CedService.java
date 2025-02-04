package com.estf.edoctorat.services;
import com.estf.edoctorat.dto.CedDTO;
import com.estf.edoctorat.mappers.CedMapper;
import com.estf.edoctorat.models.Ced;
import com.estf.edoctorat.repositories.CedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CedService {
    @Autowired
    private CedRepository cedRepository;
    private final CedMapper cedMapper = CedMapper.INSTANCE;

    public List<CedDTO> getCeds() {
        return cedRepository.findAll().stream()
                .map(cedMapper::cedToCedDTO)
                .collect(Collectors.toList());
    }

    public CedDTO getCed(Long id) {
        Optional<Ced> ced = cedRepository.findById(id);
        return ced.map(cedMapper::cedToCedDTO).orElse(null);
    }
    public CedDTO createCed(CedDTO cedDTO){
        Ced ced = cedMapper.cedDTOToCed(cedDTO);
        return   cedMapper.cedToCedDTO(cedRepository.save(ced));
    }
}