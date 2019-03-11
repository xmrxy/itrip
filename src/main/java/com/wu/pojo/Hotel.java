package com.wu.pojo;

public class Hotel {

    private Integer id;
    private Integer floorPrice;
    private Integer streetId;
    private Integer regionId;
    private Integer star;
    private Integer styleId;
    private Integer singleCount;
    private Integer doubleCount;
    private String hotelName;
    private Integer floorPriceId;
    private String hotelPic;

    private Integer startRow;
    private Integer pageSize;

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Hotel() {
    }

    public Hotel(Integer id, Integer floorPrice, Integer streetId, Integer regionId, Integer star, Integer styleId, Integer singleCount, Integer doubleCount, String hotelName, Integer floorPriceId, String hotelPic) {
        this.id = id;
        this.floorPrice = floorPrice;
        this.streetId = streetId;
        this.regionId = regionId;
        this.star = star;
        this.styleId = styleId;
        this.singleCount = singleCount;
        this.doubleCount = doubleCount;
        this.hotelName = hotelName;
        this.floorPriceId = floorPriceId;
        this.hotelPic = hotelPic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(Integer floorPrice) {
        this.floorPrice = floorPrice;
    }

    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId(Integer streetId) {
        this.streetId = streetId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public Integer getSingleCount() {
        return singleCount;
    }

    public void setSingleCount(Integer singleCount) {
        this.singleCount = singleCount;
    }

    public Integer getDoubleCount() {
        return doubleCount;
    }

    public void setDoubleCount(Integer doubleCount) {
        this.doubleCount = doubleCount;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getHotelPic() {
        return hotelPic;
    }

    public void setHotelPic(String hotelPic) {
        this.hotelPic = hotelPic;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Integer getFloorPriceId() {
        return floorPriceId;
    }

    public void setFloorPriceId(Integer floorPriceId) {
        this.floorPriceId = floorPriceId;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", floorPrice=" + floorPrice +
                ", streetId=" + streetId +
                ", regionId=" + regionId +
                ", star=" + star +
                ", styleId=" + styleId +
                ", singleCount=" + singleCount +
                ", doubleCount=" + doubleCount +
                ", hotelName='" + hotelName + '\'' +
                ", floorPriceId=" + floorPriceId +
                ", hotelPic='" + hotelPic + '\'' +
                ", startRow=" + startRow +
                ", pageSize=" + pageSize +
                '}';
    }
}
