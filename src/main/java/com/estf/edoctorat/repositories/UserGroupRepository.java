package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.User;
import com.estf.edoctorat.models.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    List<UserGroup> findByUser(User user);
}
