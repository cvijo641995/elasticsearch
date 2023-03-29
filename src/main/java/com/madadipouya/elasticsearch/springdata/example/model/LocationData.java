package com.madadipouya.elasticsearch.springdata.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocationData {

    @JsonProperty("lat")
    private double lat;
    @JsonProperty("lon")
    private double lon;

    public LocationData(Double latitude, Double longitude) {
        this.lat = latitude;
        this.lon = longitude;
    }
}
