package com.madadipouya.elasticsearch.springdata.example.controller;

import com.madadipouya.elasticsearch.springdata.example.model.LocationData;
import com.madadipouya.elasticsearch.springdata.example.model.Park;
import com.madadipouya.elasticsearch.springdata.example.service.ParkService;
import lombok.Data;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/parks")
public class ParkControler {

    private final ParkService parkService;

    public ParkControler(ParkService parkService) {
        this.parkService = parkService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/load")
    public void loadAllParks() {
        parkService.load();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/nearest")
    public Park nearest(@RequestParam(value = "lat") Double lat, @RequestParam(value = "lon") Double lon) {
        return parkService.getNearest(lat, lon);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/radius")
    public int radius(@RequestParam(value = "lat") Double lat, @RequestParam(value = "lon") Double lon) {
        return parkService.getInRadius(lat, lon);
    }
}
