package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.GroupPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupPermissionRepository extends JpaRepository<GroupPermission, Long> {
}
