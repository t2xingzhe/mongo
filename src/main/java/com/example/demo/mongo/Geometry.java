package com.example.demo.mongo;

import java.util.List;

/**
 * Created by Administrator on 2021/2/16 0016.
 */
public class Geometry {
    private String type;
    private List<Double> coordinates;

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
