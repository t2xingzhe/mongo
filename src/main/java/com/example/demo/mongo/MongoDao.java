package com.example.demo.mongo;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2021/2/16 0016.
 */
@Component
public class MongoDao {

    @Resource
    private MongoTemplate mongoTemplate;

    public Search findById(String id){
        Query query = new Query(Criteria.where("id").is(id));
        Search search = mongoTemplate.findOne(query, Search.class);
        return search;
    }
}
