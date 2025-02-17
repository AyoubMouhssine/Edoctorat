package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.Diplome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface DiplomeRepository extends JpaRepository<Diplome, Long> {
    List<Diplome> findByCandidatId(Long candidatId);
    Page<Diplome> findByCandidatId(Long candidatId, Pageable pageable);

}