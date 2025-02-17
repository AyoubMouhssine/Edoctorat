package com.estf.edoctorat.mappers;

import com.estf.edoctorat.dto.*;
import com.estf.edoctorat.models.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SujetMapper {
    SujetMapper INSTANCE = Mappers.getMapper(SujetMapper.class);

    CedDTO cedToCedDTO(Ced ced);
    Ced cedDTOToCed(CedDTO cedDTO);

    ProfesseurDTO professeurToProfesseurDTO(Professeur professeur);
    Professeur professeurDTOToProfesseur(ProfesseurDTO professeurDTO);

    FormationDoctoraleDTO formationDoctoraleToFormationDoctoraleDTO(FormationDoctorale formationDoctorale);
    FormationDoctorale formationDoctorialeDTOToFormationDoctoriale(FormationDoctoraleDTO formationDoctoraleDTO);

    SujetDTO sujetToSujetDTO(Sujet sujet);
    Sujet sujetDTOToSujet(SujetDTO sujetDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSujetFromDTO(SujetDTO sujetDTO, @MappingTarget Sujet sujet);
}