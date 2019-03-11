package com.wu.service.impl;


import com.wu.dao.StreetMapper;
import com.wu.pojo.Street;
import com.wu.service.StreetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StreetServiceImpl implements StreetService {
    @Resource
    private StreetMapper streetMapper;


    @Override
    public List<Street> findStreetByRegion(Integer regionId) {
        return streetMapper.getStreetByRegion(regionId);
    }
}
