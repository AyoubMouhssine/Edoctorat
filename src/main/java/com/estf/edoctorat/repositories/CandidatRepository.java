package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Long> {
    Optional<Candidat> findByUserId(Long userId);
}