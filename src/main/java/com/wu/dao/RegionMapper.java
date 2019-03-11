package com.wu.dao;

import com.wu.pojo.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegionMapper {
    /**
     * 查询区域
     * @return
     */
    List<Region> getRegion();

    /**
     * 根据regionId查询region
     * @param id
     * @return
     */
    Region getRegionById(@Param("id") Integer id);
}
