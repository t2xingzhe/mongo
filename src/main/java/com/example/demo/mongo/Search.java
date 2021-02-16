package com.example.demo.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2021/2/16 0016.
 */
@Document(collection = "geo_json")
public class Search implements Serializable {

    @Id
    private String id;

    private String case_id;

    private Date create_dt;

    private Date forecast_dt_start;

    private Date forecast_dt_end;

    private GeoJson geo_json;

    public Date getCreate_dt() {
        return create_dt;
    }

    public void setCreate_dt(Date create_dt) {
        this.create_dt = create_dt;
    }

    public Date getForecast_dt_end() {
        return forecast_dt_end;
    }

    public void setForecast_dt_end(Date forecast_dt_end) {
        this.forecast_dt_end = forecast_dt_end;
    }

    public Date getForecast_dt_start() {
        return forecast_dt_start;
    }

    public void setForecast_dt_start(Date forecast_dt_start) {
        this.forecast_dt_start = forecast_dt_start;
    }

    public GeoJson getGeo_json() {
        return geo_json;
    }

    public void setGeo_json(GeoJson geo_json) {
        this.geo_json = geo_json;
    }

    public String getCase_id() {
        return case_id;
    }

    public void setCase_id(String case_id) {
        this.case_id = case_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
