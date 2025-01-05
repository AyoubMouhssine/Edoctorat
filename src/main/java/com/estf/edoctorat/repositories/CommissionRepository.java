package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.Commission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommissionRepository  extends JpaRepository<Commission, Long> {
}
