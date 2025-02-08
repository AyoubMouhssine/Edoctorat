// src/main/java/com/estf/edoctorat/mappers/FormationDoctoraleMapper.java
package com.estf.edoctorat.mappers;
import com.estf.edoctorat.dto.FormationDoctoraleDTO;
import com.estf.edoctorat.models.FormationDoctorale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")  // This will make it a Spring bean

public interface FormationDoctoraleMapper {
    FormationDoctoraleMapper INSTANCE = Mappers.getMapper(FormationDoctoraleMapper.class);

    @Mapping(source = "ced.id", target = "cedId")
    @Mapping(source = "etablissement.idEtablissement", target = "etablissementId")
    FormationDoctoraleDTO formationDoctoraleToFormationDoctoraleDTO(FormationDoctorale formationDoctorale);

    @Mapping(source = "cedId", target = "ced.id")
    @Mapping(source = "etablissementId", target = "etablissement.idEtablissement")
    FormationDoctorale formationDoctoraleDTOToFormationDoctorale(FormationDoctoraleDTO formationDoctoraleDTO);
    FormationDoctoraleDTO toDTO(FormationDoctorale formationDoctorale);

}