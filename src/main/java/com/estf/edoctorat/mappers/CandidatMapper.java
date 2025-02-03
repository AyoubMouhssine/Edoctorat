// src/main/java/com/estf/edoctorat/mappers/CandidatMapper.java
package com.estf.edoctorat.mappers;

import com.estf.edoctorat.dto.CandidatDTO;
import com.estf.edoctorat.models.Candidat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CandidatMapper {

    CandidatMapper INSTANCE = Mappers.getMapper(CandidatMapper.class);

    @Mapping(source = "pays.id", target = "paysId")
    @Mapping(source = "user.id", target = "userId")
    CandidatDTO candidatToCandidatDTO(Candidat candidat);

    @Mapping(source = "paysId", target = "pays.id")
    @Mapping(source = "userId", target = "user.id")
    Candidat candidatDTOToCandidat(CandidatDTO candidatDTO);
}