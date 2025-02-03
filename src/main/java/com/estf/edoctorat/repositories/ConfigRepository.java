package com.estf.edoctorat.repositories;
import com.estf.edoctorat.models.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ConfigRepository extends JpaRepository<Config, Long> {
}