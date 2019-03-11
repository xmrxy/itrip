package com.wu.dao;

import com.wu.pojo.Hotel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotelMapper {
    /**
     * 根据hotel给定的随机条件查找酒店升序
     * @param hotel
     * @return
     */
    List<Hotel> getHotelByDesc(Hotel hotel);

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
    int getHotelCount(Hotel hotel);

    /**
     * 根据id查询酒店名称
     * @param id
     * @return
     */
    String getHotelNameByid(@Param("id")Integer id);

    /**
     * 无条件获取酒店信息
     * @return
     */
    List<Hotel> getHotel();
}
