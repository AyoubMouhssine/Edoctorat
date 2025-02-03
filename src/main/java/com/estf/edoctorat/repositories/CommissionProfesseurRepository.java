package com.estf.edoctorat.repositories;


import com.estf.edoctorat.models.CommissionProfesseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionProfesseurRepository extends JpaRepository<CommissionProfesseur, Long> {
}

