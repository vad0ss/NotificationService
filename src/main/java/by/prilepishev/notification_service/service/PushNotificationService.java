package by.prilepishev.notification_service.service;

import org.springframework.stereotype.Service;

@Service("pushNotificationService")
public class PushNotificationService implements NotificationServiceInterface {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending push service: " + message);
    }

    @Override
    public String getServiceType() {
        return "push";
    }
}
