// CandidatMapper.java
package com.estf.edoctorat.mappers;

import com.estf.edoctorat.dto.CandidatDTO;
import com.estf.edoctorat.models.Candidat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = PaysMapper.class)
public interface CandidatMapper {

    CandidatMapper INSTANCE = Mappers.getMapper(CandidatMapper.class);

    @Mapping(source = "pays", target = "pays")
    @Mapping(source = "user.id", target = "userId")
    CandidatDTO candidatToCandidatDTO(Candidat candidat);

    @Mapping(source = "pays", target = "pays")
    @Mapping(source = "userId", target = "user.id")
    Candidat candidatDTOToCandidat(CandidatDTO candidatDTO);
}