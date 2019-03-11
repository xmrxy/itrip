package com.wu.service.impl;

import com.wu.dao.HotelMapper;
import com.wu.pojo.Hotel;
import com.wu.service.HotelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Resource
    private HotelMapper hotelMapper;
    @Override
    public List<Hotel> findHotelByDesc(Hotel hotel) {
        return hotelMapper.getHotelByDesc(hotel);
    }

    @Override
    public List<Hotel> getHotelByAsc(Hotel hotel) {
        return hotelMapper.getHotelByAsc(hotel);
    }

    @Override
    public int findHotelCount(Hotel hotel) {
        return hotelMapper.getHotelCount(hotel);
    }

    @Override
    public String findHotelNameByid(Integer id) {
        return hotelMapper.getHotelNameByid(id);
    }

    @Override
    public List<Hotel> findHotel() {
        return hotelMapper.getHotel();
    }
}
