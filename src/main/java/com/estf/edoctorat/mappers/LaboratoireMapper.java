package com.estf.edoctorat.mappers;
import com.estf.edoctorat.dto.LaboratoireDTO;
import com.estf.edoctorat.models.Laboratoire;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface LaboratoireMapper {
    LaboratoireMapper INSTANCE = Mappers.getMapper(LaboratoireMapper.class);

    @Mapping(source = "ced.id", target = "cedId")
    @Mapping(source = "professeur.id", target = "directeurId")
    @Mapping(source = "etablissement.idEtablissement", target = "etablissementId")
    LaboratoireDTO laboratoireToLaboratoireDTO(Laboratoire laboratoire);

    @Mapping(source = "cedId", target = "ced.id")
    @Mapping(source = "directeurId", target = "professeur.id")
    @Mapping(source = "etablissementId", target = "etablissement.idEtablissement")
    Laboratoire laboratoireDTOToLaboratoire(LaboratoireDTO laboratoireDTO);

}