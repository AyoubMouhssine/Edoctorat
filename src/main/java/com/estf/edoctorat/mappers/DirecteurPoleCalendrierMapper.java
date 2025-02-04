package com.estf.edoctorat.mappers;
import com.estf.edoctorat.dto.DirecteurPoleCalendrierDTO;
import com.estf.edoctorat.models.DirecteurPoleCalendrier;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface DirecteurPoleCalendrierMapper {
    DirecteurPoleCalendrierMapper INSTANCE = Mappers.getMapper(DirecteurPoleCalendrierMapper.class);
    DirecteurPoleCalendrierDTO directeurPoleCalendrierToDirecteurPoleCalendrierDTO(DirecteurPoleCalendrier directeurPoleCalendrier);
    DirecteurPoleCalendrier directeurPoleCalendrierDTOToDirecteurPoleCalendrier(DirecteurPoleCalendrierDTO directeurPoleCalendrierDTO);
}