package by.prilepishev.notification_service.service;

import org.springframework.stereotype.Service;

@Service("emailNotificationService")
public class EmailNotificationService implements NotificationServiceInterface {

    @Override
    public void sendNotification(String message) {
        System.out.println("Sending email notification " + message);
    }

    @Override
    public String getServiceType() {
        return "email";
    }
}
