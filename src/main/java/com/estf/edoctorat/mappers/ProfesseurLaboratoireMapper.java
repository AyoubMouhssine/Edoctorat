package com.estf.edoctorat.mappers;
import com.estf.edoctorat.dto.ProfesseurLaboratoireDTO;
import com.estf.edoctorat.models.ProfesseurLaboratoire;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ProfesseurLaboratoireMapper {
    ProfesseurLaboratoireMapper INSTANCE = Mappers.getMapper(ProfesseurLaboratoireMapper.class);
    ProfesseurLaboratoireDTO professeurLaboratoireToProfesseurLaboratoireDTO(ProfesseurLaboratoire professeurLaboratoire);
    ProfesseurLaboratoire professeurLaboratoireDTOToProfesseurLaboratoire(ProfesseurLaboratoireDTO professeurLaboratoireDTO);
}