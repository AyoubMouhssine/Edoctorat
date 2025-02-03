package com.estf.edoctorat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatPostuler extends JpaRepository<com.estf.edoctorat.models.CandidatPostuler, Long> {
}
