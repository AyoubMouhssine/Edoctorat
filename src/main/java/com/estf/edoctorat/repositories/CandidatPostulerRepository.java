package com.estf.edoctorat.repositories;
import com.estf.edoctorat.models.CandidatPostuler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatPostulerRepository extends JpaRepository<CandidatPostuler, Long> {
    List<CandidatPostuler> findByCandidatId(Long candidatId);
}