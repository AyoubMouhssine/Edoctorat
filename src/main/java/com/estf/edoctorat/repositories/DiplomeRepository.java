// src/main/java/com/estf/edoctorat/repositories/DiplomeRepository.java
package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.Diplome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiplomeRepository extends JpaRepository<Diplome, Long> {
    List<Diplome> findByCandidatId(Long candidatId);
}