package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);
    @Query("SELECT u FROM User u WHERE u.email = :identifier OR u.username = :identifier")
    Optional<User> findByEmailOrUsername(@Param("identifier") String identifier);
    Boolean existsByEmail(String email);
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.userGroups WHERE u.email = :email or u.username = :email")
    Optional<User> findByEmailWithGroups(@Param("email") String email);

//    @Query("SELECT u FROM User u LEFT JOIN FETCH u.userGroups ug LEFT JOIN FETCH ug.authGroup WHERE u.email = :email")
//    Optional<User> findByEmailWithGroups(@Param("email") String email);
}
