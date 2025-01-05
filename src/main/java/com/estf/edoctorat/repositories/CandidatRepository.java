package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatRepository extends JpaRepository<Candidat, Long> {
}
