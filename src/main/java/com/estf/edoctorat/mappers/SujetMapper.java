package com.estf.edoctorat.mappers;

import com.estf.edoctorat.dto.SujetDTO;
import com.estf.edoctorat.models.Sujet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SujetMapper {

    @Mapping(source = "professeur", target = "professeur")
    @Mapping(source = "codirecteur", target = "coDirecteur")
    @Mapping(source = "formationDoctorale", target = "formationDoctorale")
    SujetDTO toDTO(Sujet sujet);
}

