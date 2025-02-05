package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.Config;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<Config, Long> {
}