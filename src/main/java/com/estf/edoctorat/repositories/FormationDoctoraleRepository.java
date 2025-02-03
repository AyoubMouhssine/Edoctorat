package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.FormationDoctorale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationDoctoraleRepository extends JpaRepository<FormationDoctorale, Long> {
}
