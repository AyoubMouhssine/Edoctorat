package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
