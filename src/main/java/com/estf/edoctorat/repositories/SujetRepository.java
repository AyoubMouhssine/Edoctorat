package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.Sujet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SujetRepository extends JpaRepository<Sujet, Long> {

    @Query("SELECT s FROM Sujet s ORDER BY s.id ASC")
    List<Sujet> findSujetsWithPagination(@Param("limit") int limit, @Param("offset") int offset);

    List<Sujet> findByProfesseurId(Long professeurId);

}