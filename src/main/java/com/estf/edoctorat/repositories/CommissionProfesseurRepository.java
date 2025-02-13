package com.estf.edoctorat.repositories;


import com.estf.edoctorat.models.CommissionProfesseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommissionProfesseurRepository extends JpaRepository<CommissionProfesseur, Long> {
    List<CommissionProfesseur> findByProfesseurId(Long professeurId);
}

