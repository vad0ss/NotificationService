package by.prilepishev.notification_service.service;

import org.springframework.stereotype.Service;

@Service("smsNotificationService")
public class SmsNotificationService implements NotificationServiceInterface {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending sms service: " + message);
    }

    @Override
    public String getServiceType() {
        return "sms";
    }
}
