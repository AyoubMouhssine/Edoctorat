package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.AuthGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthGroupRepository extends JpaRepository<AuthGroup, Long> {
}
