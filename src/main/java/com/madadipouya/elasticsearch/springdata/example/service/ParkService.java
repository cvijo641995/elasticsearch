package com.madadipouya.elasticsearch.springdata.example.service;

import com.madadipouya.elasticsearch.springdata.example.model.LocationData;
import com.madadipouya.elasticsearch.springdata.example.model.Park;

import java.util.List;

public interface ParkService {

    void load();

    Park getNearest(Double lat, Double lon);

    int getInRadius(Double lat, Double lon);
}
