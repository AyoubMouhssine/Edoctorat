package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.ProfesseurLaboratoire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfesseurLaboratoireRepository extends JpaRepository<ProfesseurLaboratoire, Long> {
}