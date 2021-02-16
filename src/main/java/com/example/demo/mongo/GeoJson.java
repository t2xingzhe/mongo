package com.example.demo.mongo;

import java.util.List;

/**
 * Created by Administrator on 2021/2/16 0016.
 */
public class GeoJson {
    private String type;

    private List<Features> features;

    public List<Features> getFeatures() {
        return features;
    }

    public void setFeatures(List<Features> features) {
        this.features = features;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
