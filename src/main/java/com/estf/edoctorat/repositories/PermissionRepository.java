package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
