package com.estf.edoctorat.repositories;
import com.estf.edoctorat.models.CandidatPostuler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatPostulerRepository extends JpaRepository<CandidatPostuler, Long> {
    // Add this new method
    @Query("SELECT cp FROM CandidatPostuler cp " +
            "JOIN cp.sujet s " +
            "JOIN s.professeur p " +
            "JOIN p.user u " +
            "WHERE u.id = :userId")
    List<CandidatPostuler> findByProfesseurUserId(@Param("userId") Long userId);
    List<CandidatPostuler> findByCandidatId(Long candidatId);
}