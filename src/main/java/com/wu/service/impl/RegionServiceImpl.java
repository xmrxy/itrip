package com.wu.service.impl;

import com.wu.dao.RegionMapper;
import com.wu.pojo.Region;
import com.wu.service.RegionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {
    @Resource
    private RegionMapper regionMapper;

    @Override
    public List<Region> findRegion() {
        return regionMapper.getRegion();
    }

    @Override
    public Region findRegionById(Integer id) {
        return regionMapper.getRegionById(id);
    }
}
