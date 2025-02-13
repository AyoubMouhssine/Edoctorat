package com.estf.edoctorat.mappers;

import com.estf.edoctorat.dto.CandidatDTO;
import com.estf.edoctorat.models.Candidat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = PaysMapper.class)
public interface CandidatMapper {

    CandidatMapper INSTANCE = Mappers.getMapper(CandidatMapper.class);

    @Mapping(source = "user.lastName", target = "nom")
    @Mapping(source = "user.firstName", target = "prenom")
    @Mapping(source = "pays", target = "pays")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.email", target = "email")        // Map User.email to email
    CandidatDTO candidatToCandidatDTO(Candidat candidat);

    @Mapping(source = "pays", target = "pays")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "nom", target = "user.lastName")       // Map nom to User.lastName
    @Mapping(source = "prenom", target = "user.firstName")   // Map prenom to User.firstName
    @Mapping(source = "email", target = "user.email")        // Map email to User.email
    Candidat candidatDTOToCandidat(CandidatDTO candidatDTO);
}