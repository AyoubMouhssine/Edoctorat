package com.estf.edoctorat.mappers;
import com.estf.edoctorat.dto.CedDTO;
import com.estf.edoctorat.models.Ced;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface CedMapper {
    CedMapper INSTANCE = Mappers.getMapper(CedMapper.class);

    @Mapping(source = "professeur.id", target = "directeurId")
    CedDTO cedToCedDTO(Ced ced);

    @Mapping(source = "directeurId", target = "professeur.id")
    Ced cedDTOToCed(CedDTO cedDTO);
}