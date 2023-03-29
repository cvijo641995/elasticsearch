package com.madadipouya.elasticsearch.springdata.example.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Document(indexName = "parks", type = "park")
@Data
public class Park {

    @Id
    private String id;

    private String name;

    private int year;

    private GeoPoint location;

    public Park(String name, int i, GeoPoint geoPoint) {
        this.name = name;
        this.year = i;
        this.location = geoPoint;
    }

    public Park() {

    }
}
