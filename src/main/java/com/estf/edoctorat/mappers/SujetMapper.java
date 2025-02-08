package com.estf.edoctorat.mappers;

import com.estf.edoctorat.dto.SujetDTO;
import com.estf.edoctorat.models.Sujet;
import com.estf.edoctorat.models.Professeur;
import com.estf.edoctorat.models.FormationDoctorale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SujetMapper {

    // Existing method to map Sujet to SujetDTO
    @Mapping(source = "professeur", target = "professeur")
    @Mapping(source = "codirecteur", target = "coDirecteur")
    @Mapping(source = "formationDoctorale", target = "formationDoctorale")
    SujetDTO toDTO(Sujet sujet);

    // Add the toEntity method to map SujetDTO to Sujet
    @Mapping(source = "professeur", target = "professeur")
    @Mapping(source = "coDirecteur", target = "codirecteur")
    @Mapping(source = "formationDoctorale", target = "formationDoctorale")
    Sujet toEntity(SujetDTO sujetDTO);

}
