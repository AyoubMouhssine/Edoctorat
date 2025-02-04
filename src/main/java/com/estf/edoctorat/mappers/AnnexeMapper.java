package com.estf.edoctorat.mappers;

import com.estf.edoctorat.dto.AnnexeDTO;
import com.estf.edoctorat.models.Annexe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnnexeMapper {

    AnnexeMapper INSTANCE = Mappers.getMapper(AnnexeMapper.class);

    @Mapping(source = "diplome.id", target = "diplomeId")
    AnnexeDTO annexeToAnnexeDTO(Annexe annexe);
    @Mapping(source = "diplomeId", target = "diplome.id")
    Annexe annexeDTOToAnnexe(AnnexeDTO annexeDTO);
}