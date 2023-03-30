package com.elasticsearch.park.service;

import com.elasticsearch.park.model.Park;

public interface ParkService {

    void load();

    Park getNearest(Double lat, Double lon);

    int getInRadius(Double lat, Double lon);
}
