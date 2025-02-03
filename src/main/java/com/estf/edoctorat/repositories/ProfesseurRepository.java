package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.Professeur;
import com.estf.edoctorat.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {
    Optional<Professeur> findByUser(User user);

    @Query("SELECT p FROM Professeur p WHERE p.user.email = :email")
    Optional<Professeur> findByUserEmail(@Param("email") String email);

    @Query("SELECT p FROM Professeur p WHERE p.labo_id = :labID")
    Page<Professeur> findByLabo_id(@Param("labID") Long labID, Pageable pageable);

    @Query("select P from Professeur P where P.labo_id = :labID")
    List<Professeur> findByLabo_id(@Param("labID") Long labID);
}
