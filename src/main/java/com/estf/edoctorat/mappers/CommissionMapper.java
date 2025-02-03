package com.estf.edoctorat.mappers;
import com.estf.edoctorat.dto.CommissionDTO;
import com.estf.edoctorat.models.Commission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface CommissionMapper {
    CommissionMapper INSTANCE = Mappers.getMapper(CommissionMapper.class);

    @Mapping(source = "laboratoire.id", target = "laboratoireId")
    CommissionDTO commissionToCommissionDTO(Commission commission);
    @Mapping(source = "laboratoireId", target = "laboratoire.id")
    Commission commissionDTOToCommission(CommissionDTO commissionDTO);
}