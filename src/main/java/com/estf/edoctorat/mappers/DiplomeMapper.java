package com.estf.edoctorat.mappers;
import com.estf.edoctorat.dto.DiplomeDTO;
import com.estf.edoctorat.models.Diplome;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper(uses = {AnnexeMapper.class})
public interface DiplomeMapper {
    DiplomeMapper INSTANCE = Mappers.getMapper(DiplomeMapper.class);

    @Mapping(source = "mentien", target = "mention")
    @Mapping(source = "moyenGenerale", target = "moyen_generale")
    DiplomeDTO diplomeToDiplomeDTO(Diplome diplome);

    @Mapping(source = "mention", target = "mentien")
    @Mapping(source = "moyen_generale", target = "moyenGenerale")
    Diplome diplomeDTOToDiplome(DiplomeDTO diplomeDTO);
}