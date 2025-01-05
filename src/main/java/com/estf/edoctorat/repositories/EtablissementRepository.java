package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtablissementRepository extends JpaRepository<Etablissement, Long> {
}
