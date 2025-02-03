package com.estf.edoctorat.services;

import com.estf.edoctorat.dto.AnnexeDTO;
import com.estf.edoctorat.mappers.AnnexeMapper;
import com.estf.edoctorat.models.Annexe;
import com.estf.edoctorat.repositories.AnnexeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnnexeService {

    @Autowired
    private AnnexeRepository annexeRepository;
    private final AnnexeMapper annexeMapper = AnnexeMapper.INSTANCE;

    public List<AnnexeDTO> getAnnexes() {
        return annexeRepository.findAll().stream()
                .map(annexeMapper::annexeToAnnexeDTO)
                .collect(Collectors.toList());
    }

    public AnnexeDTO getAnnexe(Long id) {
        Optional<Annexe> annexe = annexeRepository.findById(id);
        return annexe.map(annexeMapper::annexeToAnnexeDTO).orElse(null);
    }
    public AnnexeDTO createAnnexe(AnnexeDTO annexeDTO){
        Annexe annexe =  annexeMapper.annexeDTOToAnnexe(annexeDTO);
        return  annexeMapper.annexeToAnnexeDTO(annexeRepository.save(annexe));
    }
    public AnnexeDTO updateAnnexe(Long id, AnnexeDTO annexeDTO) {
        Optional<Annexe> existingAnnexe = annexeRepository.findById(id);
        if (existingAnnexe.isPresent()) {
            Annexe annexe =   annexeMapper.annexeDTOToAnnexe(annexeDTO);
            annexe.setId(existingAnnexe.get().getId());
            return annexeMapper.annexeToAnnexeDTO(annexeRepository.save(annexe));
        } else {
            return null;
        }
    }
    public boolean deleteAnnexe(Long id){
        if(annexeRepository.existsById(id))
        {annexeRepository.deleteById(id);
            return true;}
        else return false;
    }
}