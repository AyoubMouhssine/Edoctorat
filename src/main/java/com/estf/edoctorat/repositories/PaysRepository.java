package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaysRepository extends JpaRepository<Pays,Long> {
    Optional<Pays> findByNom(String nom);
}