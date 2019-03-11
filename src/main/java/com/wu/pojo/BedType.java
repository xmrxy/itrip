package com.wu.pojo;

public class BedType {
    private Integer id;
    private String bed;

    public BedType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BedType{" +
                "id=" + id +
                ", bed='" + bed + '\'' +
                '}';
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }
}
