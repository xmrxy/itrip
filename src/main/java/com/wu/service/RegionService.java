package com.wu.service;

import com.wu.pojo.Region;

import java.util.List;

public interface RegionService {


    /**
     * 查询区域
     * @return
     */
    List<Region> findRegion();

    /**
     * 根据regionId查询region
     * @param id
     * @return
     */
    Region findRegionById( Integer id);
}
