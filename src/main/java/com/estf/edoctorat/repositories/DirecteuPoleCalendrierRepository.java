package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.DirecteurPoleCalendrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirecteuPoleCalendrierRepository extends JpaRepository<DirecteurPoleCalendrier, Long> {
}