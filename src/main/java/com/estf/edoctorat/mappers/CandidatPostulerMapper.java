package com.estf.edoctorat.mappers;

import com.estf.edoctorat.dto.CandidatPostulerDTO;
import com.estf.edoctorat.models.CandidatPostuler;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CandidatMapper.class, SujetMapper.class})
public interface CandidatPostulerMapper {
    CandidatPostulerMapper INSTANCE = Mappers.getMapper(CandidatPostulerMapper.class);

    @Mapping(source = "candidat", target = "candidat")
    @Mapping(source = "sujet", target = "sujet")
    CandidatPostulerDTO candidatPostulerToCandidatPostulerDTO(CandidatPostuler candidatPostuler);

    @Mapping(source = "candidat", target = "candidat")
    @Mapping(source = "sujet", target = "sujet")
    CandidatPostuler candidatPostulerDTOToCandidatPostuler(CandidatPostulerDTO candidatPostulerDTO);
}