package com.wu.dao;

import com.wu.pojo.House;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseMapper {
    /**
     * 根据动态条件查询房型
     * @param house
     * @return
     */
    List<House> getHouse(House house);

    /**
     * 根据房间id查询房间价格
     * @param houseId
     * @return
     */
    String getHousePrice(@Param("houseId") Integer houseId);

    /**
     * 根据房间id查询房间名称
     * @param houseId
     * @return
     */
    String getHouseName(@Param("houseId") Integer houseId);

    /**
     * 根据houseid查询房间的库存
     * @param houseId
     * @return
     */
    int getHouseCount(@Param("houseId") Integer houseId);
}
