package com.elasticsearch.park.repository;

import com.elasticsearch.park.model.Park;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkRepository extends ElasticsearchRepository<Park, String> {

}
