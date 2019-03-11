package com.wu.pojo;

public class Region {
    private int id;
    private String place;

    public Region() {
    }

    public Region(int id, String place) {
        this.id = id;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", place='" + place + '\'' +
                '}';
    }
}
