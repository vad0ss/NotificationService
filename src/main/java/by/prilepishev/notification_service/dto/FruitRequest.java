package by.prilepishev.notification_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FruitRequest {

    @JsonProperty("farmId")
    private String farmId;

    public FruitRequest() {
    }

    public FruitRequest(String farmId) {
        this.farmId = farmId;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }
}
