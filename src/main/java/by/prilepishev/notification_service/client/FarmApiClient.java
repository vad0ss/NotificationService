package by.prilepishev.notification_service.client;

import by.prilepishev.notification_service.dto.FarmRequest;
import by.prilepishev.notification_service.dto.FarmResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FarmApiClient {

    private final RestTemplate restTemplate;

    @Value("${farm.api.base-url:http://localhost:8080}")
    private String farmApiBaseUrl;

    public FarmApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FarmResponse createForm(FarmRequest farmRequest) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<FarmRequest> requestEntity = new HttpEntity<>(farmRequest, headers);

            ResponseEntity<FarmResponse> response = restTemplate.exchange(
              farmApiBaseUrl + "/api/farms",
                    HttpMethod.POST,
                    requestEntity,
                    FarmResponse.class
            );

            FarmResponse farmResponse = response.getBody();
            System.out.println("Farm created successfully: " + farmResponse.getName());
            return farmResponse;

        } catch (Exception error) {
            System.err.println("Error creating farm: " + error.getMessage());
            throw new RuntimeException("Failed to create farm", error);
        }
    }

}
