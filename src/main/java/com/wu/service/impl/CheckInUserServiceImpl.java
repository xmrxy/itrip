package com.wu.service.impl;

import com.wu.dao.CheckInUserMapper;
import com.wu.pojo.CheckInUser;
import com.wu.service.CheckInUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CheckInUserServiceImpl implements CheckInUserService {
    @Resource
    private CheckInUserMapper checkInUserMapper;
    @Override
    public int addCheckInUser(CheckInUser checkInUser) {
        return checkInUserMapper.addCheckInUser(checkInUser);
    }

    @Override
    public List<CheckInUser> findCheckInUserByOrderId(Integer orderId) {
        return checkInUserMapper.getCheckInUserByOrderId(orderId);
    }
}
