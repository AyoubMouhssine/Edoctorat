// src/main/java/com/estf/edoctorat/mappers/DiplomeMapper.java
package com.estf.edoctorat.mappers;
import com.estf.edoctorat.dto.DiplomeDTO;
import com.estf.edoctorat.models.Diplome;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface DiplomeMapper {
    DiplomeMapper INSTANCE = Mappers.getMapper(DiplomeMapper.class);

    @Mapping(source = "candidat.id", target = "candidatId")
    DiplomeDTO diplomeToDiplomeDTO(Diplome diplome);

    @Mapping(source = "candidatId", target = "candidat.id")
    Diplome diplomeDTOToDiplome(DiplomeDTO diplomeDTO);
}