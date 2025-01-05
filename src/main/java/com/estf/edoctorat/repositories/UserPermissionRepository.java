package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPermissionRepository extends JpaRepository<UserPermission, Long> {
}
