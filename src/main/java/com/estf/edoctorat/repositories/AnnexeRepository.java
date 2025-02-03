package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.Annexe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnnexeRepository extends JpaRepository<Annexe, Long> {
}