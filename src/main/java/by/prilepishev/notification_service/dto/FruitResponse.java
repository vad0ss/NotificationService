package by.prilepishev.notification_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FruitResponse {

    @JsonProperty("farmId")
    private String farmId;

    public FruitResponse() {
    }

    public FruitResponse(String farmId) {
        this.farmId = farmId;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }
}
