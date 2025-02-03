package com.estf.edoctorat.mappers;
import com.estf.edoctorat.dto.ExaminerDTO;
import com.estf.edoctorat.models.Examiner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ExaminerMapper {
    ExaminerMapper INSTANCE = Mappers.getMapper(ExaminerMapper.class);
    @Mapping(source = "commission.id", target = "commissionId")
    @Mapping(source = "sujet.id", target = "sujetId")
    @Mapping(source = "candidat.id", target = "candidatId")
    ExaminerDTO examinerToExaminerDTO(Examiner examiner);

    @Mapping(source = "commissionId", target = "commission.id")
    @Mapping(source = "sujetId", target = "sujet.id")
    @Mapping(source = "candidatId", target = "candidat.id")
    Examiner examinerDTOToExaminer(ExaminerDTO examinerDTO);
}