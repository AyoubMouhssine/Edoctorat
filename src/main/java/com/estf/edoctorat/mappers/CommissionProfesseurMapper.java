package com.estf.edoctorat.mappers;
import com.estf.edoctorat.dto.CommissionProfesseurDTO;
import com.estf.edoctorat.models.CommissionProfesseur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommissionProfesseurMapper {
    CommissionProfesseurMapper INSTANCE = Mappers.getMapper(CommissionProfesseurMapper.class);
    @Mapping(source = "commission.id", target = "commissionId")
    @Mapping(source = "professeur.id", target = "professeurId")
    @Mapping(source = "commission.dateCommission", target = "dateCommission")
    @Mapping(source = "commission.lieu", target = "lieu")
    @Mapping(source = "commission.heure", target = "heure")
    CommissionProfesseurDTO commissionProfesseurToCommissionProfesseurDTO(CommissionProfesseur commissionProfesseur);



    @Mapping(source = "commissionId", target = "commission.id")
    @Mapping(source = "professeurId", target = "professeur.id")
    CommissionProfesseur commissionProfesseurDTOToCommissionProfesseur(CommissionProfesseurDTO commissionProfesseurDTO);
}