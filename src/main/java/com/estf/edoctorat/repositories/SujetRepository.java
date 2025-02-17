package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.Sujet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SujetRepository extends JpaRepository<Sujet, Long> {
    Page<Sujet> findByPublierTrue(Pageable pageable);

}
