package com.proaimltd.mybatis.service.impl;

import com.proaimltd.mybatis.model.entity.Car;
import com.proaimltd.mybatis.model.mapper.CarMapper;
import com.proaimltd.mybatis.service.ICarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ziming.xing
 * @date: 2019/7/14 19:58
 */
@Slf4j
@Service
public class CarServiceImpl implements ICarService {
    @Autowired
    private CarMapper carMapper;

    @Override
    public int deleteCarByPrimaryKey(Long id) {
        return carMapper.deleteCarByPrimaryKey(id);
    }

    @Override
    public int deleteCarByUid(Long uid) {
        return carMapper.deleteCarByUid(uid);
    }

    @Override
    public int insertCar(Car record) {
        return 0;
    }

    @Override
    public int insertCarSelective(Car record) {
        return 0;
    }

    @Override
    public Car selectCarByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public Car selectCarsByUserId(Long uid) {
        return null;
    }

    @Override
    public int updateCarByPrimaryKeySelective(Car record) {
        return 0;
    }

    @Override
    public int updateCarByPrimaryKey(Car record) {
        return 0;
    }
}
