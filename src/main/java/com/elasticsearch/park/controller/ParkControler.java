package com.elasticsearch.park.controller;

import com.elasticsearch.park.service.ParkService;
import com.elasticsearch.park.model.Park;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
