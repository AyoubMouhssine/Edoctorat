package com.estf.edoctorat.services;
import com.estf.edoctorat.dto.DirecteurPoleCalendrierDTO;
import com.estf.edoctorat.mappers.DirecteurPoleCalendrierMapper;
import com.estf.edoctorat.models.DirecteurPoleCalendrier;
import com.estf.edoctorat.repositories.DirecteurPoleCalendrierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DirecteurPoleCalendrierService {
    @Autowired
    private DirecteurPoleCalendrierRepository directeurPoleCalendrierRepository;
    private final DirecteurPoleCalendrierMapper directeurPoleCalendrierMapper = DirecteurPoleCalendrierMapper.INSTANCE;

    public List<DirecteurPoleCalendrierDTO> getDirecteurPoleCalendriers() {
        return directeurPoleCalendrierRepository.findAll().stream()
                .map(directeurPoleCalendrierMapper::directeurPoleCalendrierToDirecteurPoleCalendrierDTO)
                .collect(Collectors.toList());
    }

    public DirecteurPoleCalendrierDTO getDirecteurPoleCalendrier(Long id) {
        Optional<DirecteurPoleCalendrier> directeurPoleCalendrier = directeurPoleCalendrierRepository.findById(id);
        return directeurPoleCalendrier.map(directeurPoleCalendrierMapper::directeurPoleCalendrierToDirecteurPoleCalendrierDTO).orElse(null);
    }
    public DirecteurPoleCalendrierDTO createDirecteurPoleCalendrier(DirecteurPoleCalendrierDTO directeurPoleCalendrierDTO){
        DirecteurPoleCalendrier directeurPoleCalendrier = directeurPoleCalendrierMapper.directeurPoleCalendrierDTOToDirecteurPoleCalendrier(directeurPoleCalendrierDTO);
        return  directeurPoleCalendrierMapper.directeurPoleCalendrierToDirecteurPoleCalendrierDTO(directeurPoleCalendrierRepository.save(directeurPoleCalendrier));
    }
    public DirecteurPoleCalendrierDTO updateDirecteurPoleCalendrier(Long id, DirecteurPoleCalendrierDTO directeurPoleCalendrierDTO) {
        Optional<DirecteurPoleCalendrier> existingDirecteurPoleCalendrier = directeurPoleCalendrierRepository.findById(id);
        if (existingDirecteurPoleCalendrier.isPresent()) {
            DirecteurPoleCalendrier directeurPoleCalendrier = directeurPoleCalendrierMapper.directeurPoleCalendrierDTOToDirecteurPoleCalendrier(directeurPoleCalendrierDTO);
            directeurPoleCalendrier.setId(existingDirecteurPoleCalendrier.get().getId());
            return directeurPoleCalendrierMapper.directeurPoleCalendrierToDirecteurPoleCalendrierDTO(directeurPoleCalendrierRepository.save(directeurPoleCalendrier));
        } else {
            return null;
        }
    }
    public boolean deleteDirecteurPoleCalendrier(Long id){
        if(directeurPoleCalendrierRepository.existsById(id))
        {directeurPoleCalendrierRepository.deleteById(id);
            return true;}
        else return false;
    }

}