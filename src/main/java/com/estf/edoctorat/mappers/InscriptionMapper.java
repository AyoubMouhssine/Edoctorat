package com.estf.edoctorat.mappers;
import com.estf.edoctorat.dto.InscriptionDTO;
import com.estf.edoctorat.models.Inscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface InscriptionMapper {
    InscriptionMapper INSTANCE = Mappers.getMapper(InscriptionMapper.class);
    @Mapping(source = "candidat.id", target = "candidatId")
    @Mapping(source = "sujet.id", target = "sujetId")
    InscriptionDTO inscriptionToInscriptionDTO(Inscription inscription);
    @Mapping(source = "candidatId", target = "candidat.id")
    @Mapping(source = "sujetId", target = "sujet.id")
    Inscription inscriptionDTOToInscription(InscriptionDTO inscriptionDTO);
}