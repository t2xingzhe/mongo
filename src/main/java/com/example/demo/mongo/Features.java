package com.example.demo.mongo;

/**
 * Created by Administrator on 2021/2/16 0016.
 */
public class Features {
    private String type;

    private Geometry geometry;

    private Property properties;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Property getProperties() {
        return properties;
    }

    public void setProperties(Property properties) {
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
