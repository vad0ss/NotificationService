package by.prilepishev.notification_service.controller;

import by.prilepishev.notification_service.service.NotificationManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import by.prilepishev.notification_service.dto.FarmResponse;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationManagerService notificationManagerService;

    @Autowired
    public NotificationController(NotificationManagerService notificationManagerService) {
        this.notificationManagerService = notificationManagerService;
    }

    @PostMapping("/create-farm")
    public FarmResponse createFarm(@RequestBody Map<String, String> request) {
        return notificationManagerService.createFarmAndNotify(request);
    }

    @PostMapping("/email")
    public ResponseEntity<Map<String, String>> sendEmailNotification(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        notificationManagerService.sendEmailNotification(message);

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("type", "email");
        response.put("message", "Email notification sent");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/sms")
    public ResponseEntity<Map<String, String>> sendSmsNotification(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        notificationManagerService.sendSmsNotification(message);

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("type", "sms");
        response.put("message", "SMS notification sent");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/push")
    public ResponseEntity<Map<String, String>> sendPushNotification(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        notificationManagerService.sendPushNotification(message);

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("type", "push");
        response.put("message", "Push notification sent");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/all")
    public ResponseEntity<Map<String, String>> sendAllNotification(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        notificationManagerService.sendNotificationToAll(message);

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("type", "All");
        response.put("message", "All notifications sent");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/by-type/{serviceType}")
    public ResponseEntity<Map<String, String>> sendNotificationByType(
            @PathVariable String serviceType,
            @RequestBody Map<String, String> request) {

        String message = request.get("message");
        notificationManagerService.sendNotificationByType(serviceType, message);

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("type", serviceType);
        response.put("message", "Notifications sent to " + serviceType);

        return ResponseEntity.ok(response);
    }

}
