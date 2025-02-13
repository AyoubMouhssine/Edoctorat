package com.estf.edoctorat.mappers;
import com.estf.edoctorat.dto.CommissionProfesseurDTO;
import com.estf.edoctorat.dto.ProfesseurDTO;
import com.estf.edoctorat.models.CommissionProfesseur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommissionProfesseurMapper {
    CommissionProfesseurMapper INSTANCE = Mappers.getMapper(CommissionProfesseurMapper.class);

    @Mapping(source = "commission.id", target = "commissionId")
    @Mapping(source = "commission.dateCommission", target = "dateCommission")
    @Mapping(source = "commission.lieu", target = "lieu")
    @Mapping(source = "commission.heure", target = "heure")
    @Mapping(target = "participants", expression = "java(mapProfessorsToDTOs(commissionProfesseur.getCommission().getCommissionProfesseurs()))")
    CommissionProfesseurDTO commissionProfesseurToCommissionProfesseurDTO(CommissionProfesseur commissionProfesseur);

    @Mapping(source = "professeur.userId", target = "userId")
    @Mapping(source = "professeur.user.firstName", target = "nom")
    @Mapping(source = "professeur.user.lastName", target = "prenom")
    ProfesseurDTO professeurToProfesseurDTO(CommissionProfesseur commissionProfesseur);

    default List<ProfesseurDTO> mapProfessorsToDTOs(List<CommissionProfesseur> commissionProfesseurs) {
        return commissionProfesseurs.stream()
                .map(this::professeurToProfesseurDTO)
                .toList();
    }
}