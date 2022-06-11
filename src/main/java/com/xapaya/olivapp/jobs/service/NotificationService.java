package com.xapaya.olivapp.jobs.service;

import com.xapaya.olivapp.jobs.model.Notification;
import com.xapaya.olivapp.jobs.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void addNotification(String entityId, String operation) {
        notificationRepository.save(Notification.builder()
                .created(Instant.now())
                .entityId(entityId)
                .userId(UserUtils.getCurrentUserId())
                .operation(operation)
                .build());
    }

    public void deleteNotificationByUser() {
        List<Notification> notifications = notificationRepository.findByUserId(UserUtils.getCurrentUserId());
        notificationRepository.deleteAll(notifications);
    }
}
