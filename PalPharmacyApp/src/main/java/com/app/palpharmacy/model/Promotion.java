package com.app.palpharmacy.model;

public class Promotion {
    private String pharname;
    private String title;
    private String endtime;
    private String decs;
    private String image;

    public Promotion() {

    }


    public String getDecs() {
        return decs;
    }

    public void setDecs(String decs) {
        this.decs = decs;
    }

    public String getPharname() {
        return pharname;
    }

    public void setPharname(String pharname) {
        this.pharname = pharname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
