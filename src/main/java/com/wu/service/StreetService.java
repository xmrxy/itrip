package com.wu.service;

import com.wu.pojo.Street;

import java.util.List;

public interface StreetService {

    /**
     * 根据regionId查询对应的地点
     * @param regionId
     * @return
     */
    List<Street> findStreetByRegion(Integer regionId);
}
