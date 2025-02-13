package com.estf.edoctorat.mappers;
import com.estf.edoctorat.dto.ProfesseurDTO;
import com.estf.edoctorat.models.Professeur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfesseurMapper {
    ProfesseurMapper INSTANCE = Mappers.getMapper(ProfesseurMapper.class);

    @Mapping(source = "etablissement.idEtablissement", target = "etablissementId")
    @Mapping(source = "user.firstName", target = "prenom")
    @Mapping(source = "user.lastName", target = "nom")
    @Mapping(source = "user.id", target = "userId")
    ProfesseurDTO professeurToProfesseurDTO(Professeur professeur);

    @Mapping(source = "etablissementId", target = "etablissement.idEtablissement")
    @Mapping(source = "userId", target = "user.id")
    Professeur professeurDTOToProfesseur(ProfesseurDTO professeurDTO);
}