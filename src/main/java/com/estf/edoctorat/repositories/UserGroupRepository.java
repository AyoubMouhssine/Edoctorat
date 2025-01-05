package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
}
