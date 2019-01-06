package com.example.adrianantonescu.qa.util;

public class Coordonate {
    private Double latitude;
    private Double longitude;
    private String building;

    public Coordonate(Double latitude, Double longitude, String building) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.building = building;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getStore() {
        return building;
    }

    public void setStore(String building) {
        this.building = building;
    }
}
