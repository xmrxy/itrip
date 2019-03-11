package com.wu.pojo;

public class House {
    private Integer id;
    private String houseName;
    private Integer bedTypeId;
    private String breakfast;
    private String broadband;
    private String policy;
    private String housePrice;
    private Integer count;
    private Integer hotelId;
    private String housePic;
    private String bed;


    public House() {
    }


    public House(Integer id, String houseName, Integer bedTypeId, String breakfast, String broadband, String policy, String housePrice, Integer count, Integer hotelId, String housePic, String bed) {
        this.id = id;
        this.houseName = houseName;
        this.bedTypeId = bedTypeId;
        this.breakfast = breakfast;
        this.broadband = broadband;
        this.policy = policy;
        this.housePrice = housePrice;
        this.count = count;
        this.hotelId = hotelId;
        this.housePic = housePic;
        this.bed = bed;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public Integer getBedTypeId() {
        return bedTypeId;
    }

    public void setBedTypeId(Integer bedTypeId) {
        this.bedTypeId = bedTypeId;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getBroadband() {
        return broadband;
    }

    public void setBroadband(String broadband) {
        this.broadband = broadband;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(String housePrice) {
        this.housePrice = housePrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHousePic() {
        return housePic;
    }

    public void setHousePic(String housePic) {
        this.housePic = housePic;
    }


    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", houseName='" + houseName + '\'' +
                ", bedTypeId=" + bedTypeId +
                ", breakfast='" + breakfast + '\'' +
                ", broadband='" + broadband + '\'' +
                ", policy='" + policy + '\'' +
                ", housePrice='" + housePrice + '\'' +
                ", count=" + count +
                ", hotelId=" + hotelId +
                ", housePic='" + housePic + '\'' +
                ", bed='" + bed + '\'' +
                '}';
    }
}
