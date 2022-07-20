package com.xapaya.olivapp.jobs.controller;

import com.xapaya.olivapp.jobs.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("")
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(notificationService.getNotificationsByCurrentUser());
    }

    @PostMapping("/read")
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<?> readNotifications() {
        return ResponseEntity.ok(notificationService.deleteNotificationByUser());
    }
}
