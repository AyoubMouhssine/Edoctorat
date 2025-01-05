package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {
}
