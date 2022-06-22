package com.xapaya.olivapp.jobs.service;

import com.xapaya.olivapp.jobs.model.Notification;
import com.xapaya.olivapp.jobs.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void addNotification(String entityId, String operation) {
        try {
            notificationRepository.save(Notification.builder()
                    .created(Instant.now())
                    .entityId(entityId)
                    .userId(UserUtils.getCurrentUserId())
                    .operation(operation)
                    .build());
        } catch (Exception e) {
            log.warn("Exception when saving Notification {}", e.getMessage());
        }
    }

    public int deleteNotificationByUser() {
        List<Notification> notifications = notificationRepository.findByUserId(UserUtils.getCurrentUserId());
        notificationRepository.deleteAll(notifications);
        return notifications.size();
    }

    public List<Notification> getNotificationsByCurrentUser() {
        return notificationRepository.findByUserId(UserUtils.getCurrentUserId());
    }
}
