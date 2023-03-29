package com.madadipouya.elasticsearch.springdata.example.repository;

import com.madadipouya.elasticsearch.springdata.example.model.Park;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkRepository extends ElasticsearchRepository<Park, String> {

}
