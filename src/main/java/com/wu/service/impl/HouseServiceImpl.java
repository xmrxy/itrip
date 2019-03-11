package com.wu.service.impl;

import com.wu.dao.HouseMapper;
import com.wu.pojo.House;
import com.wu.service.HouseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Resource
    private HouseMapper houseMapper;
    @Override
    public List<House> findHouse(House house) {
        return houseMapper.getHouse(house);
    }

    @Override
    public String findHousePrice(Integer houseId) {
        return houseMapper.getHousePrice(houseId);
    }

    @Override
    public String findHouseName(Integer houseId) {
        return houseMapper.getHouseName(houseId);
    }

    @Override
    public int findHouseCount(Integer houseId) {
        return houseMapper.getHouseCount(houseId);
    }
}
