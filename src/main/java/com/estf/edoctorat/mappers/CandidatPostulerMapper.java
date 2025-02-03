// src/main/java/com/estf/edoctorat/mappers/CandidatPostulerMapper.java
package com.estf.edoctorat.mappers;

import com.estf.edoctorat.dto.CandidatPostulerDTO;
import com.estf.edoctorat.models.CandidatPostuler;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface CandidatPostulerMapper {
    CandidatPostulerMapper INSTANCE = Mappers.getMapper(CandidatPostulerMapper.class);

    @Mapping(source = "candidat.id", target = "candidatId")
    @Mapping(source = "sujet.id", target = "sujetId")
    CandidatPostulerDTO candidatPostulerToCandidatPostulerDTO(CandidatPostuler candidatPostuler);


    @Mapping(source = "candidatId", target = "candidat.id")
    @Mapping(source = "sujetId", target = "sujet.id")
    CandidatPostuler candidatPostulerDTOToCandidatPostuler(CandidatPostulerDTO candidatPostulerDTO);
}