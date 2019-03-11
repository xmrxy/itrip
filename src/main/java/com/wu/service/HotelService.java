package com.wu.service;

import com.wu.pojo.Hotel;

import java.util.List;

public interface HotelService {
    /**
     * 根据hotel给定的随机条件查找酒店升序
     * @param hotel
     * @return
     */
    List<Hotel> findHotelByDesc(Hotel hotel);

    /**
     * 根据hotel给定的随机条件查找酒店降序
     * @param hotel
     * @return
     */
    List<Hotel> getHotelByAsc(Hotel hotel);


    /**
     * 根据hotel给定的随机条件查找酒店的数量
     * @param hotel
     * @return
     */
    int findHotelCount(Hotel hotel);

    /**
     * 根据id查询酒店名称
     * @param id
     * @return
     */
    String findHotelNameByid(Integer id);

    /**
     * 无条件获取酒店信息
     * @return
     */
    List<Hotel> findHotel();
}
