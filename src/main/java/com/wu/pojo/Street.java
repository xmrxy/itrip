package com.wu.pojo;

public class Street {
    private int id;
    private String streetName;
    private int regionId;

    public Street() {
    }

    public Street(int id, String streetName, int regionId) {
        this.id = id;
        this.streetName = streetName;
        this.regionId = regionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    @Override
    public String toString() {
        return "Street{" +
                "id=" + id +
                ", streetName='" + streetName + '\'' +
                ", regionId=" + regionId +
                '}';
    }
}
