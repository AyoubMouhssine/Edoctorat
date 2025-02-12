package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtablissementRepository extends JpaRepository<Etablissement, Long> {
}