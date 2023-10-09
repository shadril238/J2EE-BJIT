package com.nuri.notificationservice.repository;

import com.nuri.notificationservice.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Optional<Notification> findByUserIdAndId(Long userId, Long notificationId);
}
