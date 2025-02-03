// src/main/java/com/estf/edoctorat/mappers/ConfigMapper.java
package com.estf.edoctorat.mappers;
import com.estf.edoctorat.dto.ConfigDTO;
import com.estf.edoctorat.models.Config;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ConfigMapper {
    ConfigMapper INSTANCE = Mappers.getMapper(ConfigMapper.class);

    ConfigDTO configToConfigDTO(Config config);
    Config configDTOToConfig(ConfigDTO configDTO);
}