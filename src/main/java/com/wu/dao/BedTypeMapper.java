package com.wu.dao;

import com.wu.pojo.BedType;

import java.util.List;

public interface BedTypeMapper {

    /**
     * 获取床型
     * @return
     */
    List<BedType> getBed();
}
