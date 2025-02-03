package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.Examiner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExaminerRepository extends JpaRepository<Examiner, Long> {
}
