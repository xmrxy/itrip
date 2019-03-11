package com.wu.service;

import com.wu.pojo.House;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseService {
    /**
     * 根据动态条件查询房型
     * @param house
     * @return
     */
    List<House> findHouse(House house);

    /**
     * 根据房间id查询房间价格
     * @param houseId
     * @return
     */
    String findHousePrice(Integer houseId);


    /**
     * 根据房间id查询房间名称
     * @param houseId
     * @return
     */
    String findHouseName(Integer houseId);

    /**
     * 根据houseid查询房间的库存
     * @param houseId
     * @return
     */
    int findHouseCount(Integer houseId);
}
