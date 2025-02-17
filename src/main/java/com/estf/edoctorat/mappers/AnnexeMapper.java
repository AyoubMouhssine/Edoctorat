package com.estf.edoctorat.mappers;

import com.estf.edoctorat.dto.AnnexeDTO;
import com.estf.edoctorat.models.Annexe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnnexeMapper {
    AnnexeMapper INSTANCE = Mappers.getMapper(AnnexeMapper.class);

    AnnexeDTO annexeToAnnexeDTO(Annexe annexe);
    Annexe annexeDTOToAnnexe(AnnexeDTO annexeDTO);
}