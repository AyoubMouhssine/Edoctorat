package com.estf.edoctorat.services;

import com.estf.edoctorat.dto.FormationDoctoraleDTO;
import com.estf.edoctorat.dto.Result; // Import the Result class
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

    // Method to get all formation doctorales and wrap the result in a Result object
    public Result<FormationDoctoraleDTO> getFormationDoctorales() {
        List<FormationDoctorale> formationDoctorales = formationDoctoraleRepository.findAll();
        List<FormationDoctoraleDTO> formationDoctoraleDTOs = formationDoctorales.stream()
                .map(formationDoctoraleMapper::formationDoctoraleToFormationDoctoraleDTO)
                .collect(Collectors.toList());
        // Return Result with list and total count
        return new Result<>(formationDoctoraleDTOs, formationDoctoraleDTOs.size());
    }

    // Method to get formation doctorale by ID
    public FormationDoctoraleDTO getFormationDoctorale(Long id) {
        Optional<FormationDoctorale> formationDoctorale = formationDoctoraleRepository.findById(id);
        return formationDoctorale.map(formationDoctoraleMapper::formationDoctoraleToFormationDoctoraleDTO).orElse(null);
    }

    // Method to create a new formation doctorale and return the created DTO
    public FormationDoctoraleDTO createFormationDoctorale(FormationDoctoraleDTO formationDoctoraleDTO) {
        FormationDoctorale formationDoctorale = formationDoctoraleMapper.formationDoctoraleDTOToFormationDoctorale(formationDoctoraleDTO);
        FormationDoctorale savedFormationDoctorale = formationDoctoraleRepository.save(formationDoctorale);
        return formationDoctoraleMapper.formationDoctoraleToFormationDoctoraleDTO(savedFormationDoctorale);
    }
}
