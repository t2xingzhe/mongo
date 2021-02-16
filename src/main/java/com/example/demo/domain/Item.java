package com.example.demo.domain;

/**
 * Created by Administrator on 2018/4/5 0005.
 */
public class Item {
    private String img = "";
    private String id = "";
    private String title = "";
    private String actUrl = "";
    private String act = "";
    private String sId = "";

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getActUrl() {
        return actUrl;
    }

    public void setActUrl(String actUrl) {
        this.actUrl = actUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
