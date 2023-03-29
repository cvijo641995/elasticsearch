package com.madadipouya.elasticsearch.springdata.example.service.impl;

import com.madadipouya.elasticsearch.springdata.example.model.LocationData;
import com.madadipouya.elasticsearch.springdata.example.model.Park;
import com.madadipouya.elasticsearch.springdata.example.repository.ParkRepository;
import com.madadipouya.elasticsearch.springdata.example.service.ParkService;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

@Service
public class ParkServiceImpl implements ParkService {

    String csvFile = "/Users/dcvijovic/Desktop/AirTerra-doc/Spring-Data-ElasticSearch-Example/src/main/resources/LA parking lot.csv";
    String line;
    String csvSplitBy = ",";
    char quote = '\"';
    private final ParkRepository parkRepository;
    private final ElasticsearchTemplate elasticsearchTemplate;


    public ParkServiceImpl(ParkRepository parkRepository, ElasticsearchTemplate elasticsearchTemplate) {
        this.parkRepository = parkRepository;
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Override
    public void load(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy + "(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                for (int i = 0; i < data.length; i++) {
                    // Remove any surrounding quotes from the data
                    if (data[i].startsWith(String.valueOf(quote)) && data[i].endsWith(String.valueOf(quote))) {
                        data[i] = data[i].substring(1, data[i].length() - 1);
                    }
                }
                Park park = new Park();
                park.setLocation(new GeoPoint(Double.parseDouble(data[0]), Double.parseDouble(data[1])));
                park.setName(data[2]);
                park.setYear(Integer.parseInt(data[3]));
                // Do something with the data, e.g. print it to console
                parkRepository.save(park);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Park getNearest(Double lat, Double lon) {
        GeoDistanceSortBuilder sortBuilder = new GeoDistanceSortBuilder("location", lat, lon);
        sortBuilder.unit(DistanceUnit.KILOMETERS);
        sortBuilder.order(SortOrder.ASC);
        sortBuilder.geoDistance(GeoDistance.ARC);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withSort(sortBuilder)
                .withPageable(PageRequest.of(0, 1))
                .build();

        List<Park> results = elasticsearchTemplate.queryForList(searchQuery, Park.class);
        return results.get(0);
    }

    @Override
    public int getInRadius(Double lat, Double lon) {
        GeoDistanceQueryBuilder queryBuilder = new GeoDistanceQueryBuilder("location")
                .point(lat, lon)
                .distance(1, DistanceUnit.KILOMETERS);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withPageable(PageRequest.of(0, 4000))
                .build();

        List<Park> results = elasticsearchTemplate.queryForList(searchQuery, Park.class);
        return results.size();
    }
}

