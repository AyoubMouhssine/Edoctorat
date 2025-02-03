package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.Sujet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SujetRepository extends JpaRepository<Sujet, Long> {
}
