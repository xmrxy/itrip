package com.wu.service;

import com.wu.pojo.BedType;

import java.util.List;

public interface BedTypeService {
    /**
     * 获取床型
     * @return
     */
    List<BedType> findBed();
}
