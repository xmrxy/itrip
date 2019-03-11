package com.wu.service.impl;

import com.wu.dao.BedTypeMapper;
import com.wu.pojo.BedType;
import com.wu.service.BedTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BedTypeServiceImpl implements BedTypeService {

    @Resource
    private BedTypeMapper bedTypeMapper;

    @Override
    public List<BedType> findBed() {
        return bedTypeMapper.getBed();
    }
}
