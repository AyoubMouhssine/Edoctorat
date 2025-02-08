// src/main/java/com/estf/edoctorat/mappers/SujetMapper.java
package com.estf.edoctorat.mappers;

import com.estf.edoctorat.dto.SujetDTO;
import com.estf.edoctorat.models.Sujet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SujetMapper {

    SujetMapper INSTANCE = Mappers.getMapper(SujetMapper.class);

    @Mapping(source = "codirecteur.id", target = "codirecteurId")
    @Mapping(source = "formationDoctorale.id", target = "formationDoctoraleId")
    @Mapping(source = "professeur.id", target = "professeurId")
    SujetDTO sujetToSujetDTO(Sujet sujet);

    @Mapping(source = "codirecteurId", target = "codirecteur.id")
    @Mapping(source = "formationDoctoraleId", target = "formationDoctorale.id")
    @Mapping(source = "professeurId", target = "professeur.id")
    Sujet sujetDTOToSujet(SujetDTO sujetDTO);
}