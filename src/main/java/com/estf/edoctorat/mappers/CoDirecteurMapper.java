package com.estf.edoctorat.mappers;

import com.estf.edoctorat.dto.CoDirecteurDTO;
import com.estf.edoctorat.models.Professeur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CoDirecteurMapper {
    CoDirecteurMapper INSTANCE = Mappers.getMapper(CoDirecteurMapper.class);
    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.firstName", target = "nom")
    @Mapping(source = "user.lastName", target = "prenom")
    CoDirecteurDTO professeurToProfesseurDTO(Professeur professeur);

//    @Mapping(source = "etablissementId", target = "etablissement.idEtablissement")
//    @Mapping(source = "userId", target = "user.id")
//    Professeur professeurDTOToProfesseur(ProfesseurDTO professeurDTO);
}