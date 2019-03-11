package com.wu.dao;

import com.wu.pojo.Street;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StreetMapper {

    /**
     * 根据regionId查询对应的地点
     * @param regionId
     * @return
     */
    List<Street> getStreetByRegion(@Param("regionId") Integer regionId);
}
