package com.example.demo.mongo;

import java.util.Date;

/**
 * Created by Administrator on 2021/2/16 0016.
 */
public class Property {
    private Date time;
    private Long id;
    private Long mass_oil;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMass_oil() {
        return mass_oil;
    }

    public void setMass_oil(Long mass_oil) {
        this.mass_oil = mass_oil;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
