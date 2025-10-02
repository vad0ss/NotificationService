package by.prilepishev.notification_service.service;

import by.prilepishev.notification_service.client.FarmApiClient;
import by.prilepishev.notification_service.dto.FarmRequest;
import by.prilepishev.notification_service.dto.FarmResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NotificationManagerService {

      private final NotificationServiceInterface emailService;
      private final NotificationServiceInterface smsService;
      private final NotificationServiceInterface pushService;
      private final List<NotificationServiceInterface> allNotificationService;
      private final Map<String, NotificationServiceInterface> notificationServiceMap;
      private final FarmApiClient farmApiClient;

    @Autowired
    public NotificationManagerService(
            @Qualifier("emailNotificationService") NotificationServiceInterface emailService,
            @Qualifier("smsNotificationService") NotificationServiceInterface smsService,
            @Qualifier("pushNotificationService") NotificationServiceInterface pushService,
            List<NotificationServiceInterface> allNotificationService,
            Map<String, NotificationServiceInterface> notificationServiceMap,
            FarmApiClient farmApiClient) {

        this.emailService = emailService;
        this.smsService = smsService;
        this.pushService = pushService;
        this.allNotificationService = allNotificationService;
        this.notificationServiceMap = notificationServiceMap;
        this.farmApiClient = farmApiClient;
    }

    public void sendEmailNotification(String message) {
        emailService.sendNotification(message);
    }

    public void sendSmsNotification(String message) {
        smsService.sendNotification(message);
    }

    public void sendPushNotification(String message) {
        pushService.sendNotification(message);
    }

    public FarmResponse createFarmAndNotify(Map<String, String> request) {
        try {
            FarmRequest farmRequest = new FarmRequest();
            farmRequest.setName(request.get("name"));
            farmRequest.setLocation(request.get("location"));

            FarmResponse farmResponse = farmApiClient.createForm(farmRequest);

            sendNotificationToAll("Farm is created!");
            return farmResponse;
        } catch (Exception e) {
            System.err.println("Error during farm creation and notification: " + e.getMessage());
            return null;
        }
    }

    public void sendNotificationToAll(String message) {
        System.out.println("Send messages all types");
        allNotificationService.forEach(
                service ->  {
                    System.out.println("Service type: " + service.getServiceType());
                    service.sendNotification(message);
                });
    }

    public void sendNotificationByType(String serviceType, String message) {
        NotificationServiceInterface service = notificationServiceMap.get(serviceType);
        if(service != null) {
            service.sendNotification(message);
        } else {
            System.out.println("Service type " + serviceType + " not found");
        }
    }

}
