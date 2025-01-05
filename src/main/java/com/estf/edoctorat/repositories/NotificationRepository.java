package com.estf.edoctorat.repositories;

import com.estf.edoctorat.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
