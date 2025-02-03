package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.Ced;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CedRepository extends JpaRepository<Ced, Long> {
}
