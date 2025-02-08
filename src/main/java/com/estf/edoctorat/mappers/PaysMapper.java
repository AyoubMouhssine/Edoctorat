// PaysMapper.java
package com.estf.edoctorat.mappers;

import com.estf.edoctorat.dto.PaysDTO;
import com.estf.edoctorat.models.Pays;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaysMapper {
    PaysMapper INSTANCE = Mappers.getMapper(PaysMapper.class);

    PaysDTO paysToPaysDTO(Pays pays);
    Pays paysDTOToPays(PaysDTO paysDTO);
}