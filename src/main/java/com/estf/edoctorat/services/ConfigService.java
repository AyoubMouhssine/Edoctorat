package com.estf.edoctorat.services;

import com.estf.edoctorat.dto.ConfigDTO;
import com.estf.edoctorat.mappers.ConfigMapper;
import com.estf.edoctorat.models.Config;
import com.estf.edoctorat.repositories.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfigService {

    @Autowired
    private ConfigRepository configRepository;

    private final ConfigMapper configMapper = ConfigMapper.INSTANCE;

    public ConfigDTO getConfigInfo() {
        Optional<Config> config = configRepository.findById(1L);
        return config.map(configMapper::configToConfigDTO).orElse(null);
    }
}
