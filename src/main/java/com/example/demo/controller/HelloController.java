package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.mongo.MongoDao;
import com.example.demo.mongo.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/4/4 0004.
 */
@RestController
public class HelloController {

    @Autowired
    private MongoDao mongoDao;

    @GetMapping("/helloworld")
    @ResponseBody
    public Search helloworld() {
        Long start = System.currentTimeMillis();
        Search search = mongoDao.findById("5fffeadb9ade9e80af1a6750");
//        Long end = System.currentTimeMillis();
//        System.out.println(search.getCase_id() + " :" + (end-start));
//        String json = JSON.toJSONString(search);
//        System.out.println(System.currentTimeMillis()-end);
        return search;
    }
}
