package com.estf.edoctorat.mappers;

import com.estf.edoctorat.dto.EtablissementDTO;
import com.estf.edoctorat.models.Etablissement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface EtablissementMapper {
    EtablissementMapper INSTANCE = Mappers.getMapper(EtablissementMapper.class);
    EtablissementDTO etablissementToEtablissementDTO(Etablissement etablissement);
    Etablissement etablissementDTOToEtablissement(EtablissementDTO etablissementDTO);
}