package com.estf.edoctorat.services;
import com.estf.edoctorat.dto.FormationDoctoraleDTO;
import com.estf.edoctorat.mappers.FormationDoctoraleMapper;
import com.estf.edoctorat.models.FormationDoctorale;
import com.estf.edoctorat.repositories.FormationDoctoraleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FormationDoctoraleService {
    @Autowired
    private FormationDoctoraleRepository formationDoctoraleRepository;
    private final FormationDoctoraleMapper formationDoctoraleMapper = FormationDoctoraleMapper.INSTANCE;

    public List<FormationDoctoraleDTO> getFormationDoctorales() {
        return formationDoctoraleRepository.findAll().stream()
                .map(formationDoctoraleMapper::formationDoctoraleToFormationDoctoraleDTO)
                .collect(Collectors.toList());
    }
    public FormationDoctoraleDTO getFormationDoctorale(Long id) {
        Optional<FormationDoctorale> formationDoctorale = formationDoctoraleRepository.findById(id);
        return formationDoctorale.map(formationDoctoraleMapper::formationDoctoraleToFormationDoctoraleDTO).orElse(null);
    }
    public FormationDoctoraleDTO createFormationDoctorale(FormationDoctoraleDTO formationDoctoraleDTO){
        FormationDoctorale formationDoctorale =   formationDoctoraleMapper.formationDoctoraleDTOToFormationDoctorale(formationDoctoraleDTO);
        return    formationDoctoraleMapper.formationDoctoraleToFormationDoctoraleDTO(formationDoctoraleRepository.save(formationDoctorale));
    }
}