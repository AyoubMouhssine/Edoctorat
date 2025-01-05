package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
}
