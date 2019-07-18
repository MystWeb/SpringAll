package com.proaimltd.mybatis.service;

import com.proaimltd.mybatis.model.entity.Car;

/**
 * @author: ziming.xing
 * @date: 2019/7/14 19:57
 */
public interface ICarService {
    int deleteCarByPrimaryKey(Long id);

    int deleteCarByUid(Long uid);

    int insertCar(Car record);

    int insertCarSelective(Car record);

    Car selectCarByPrimaryKey(Long id);

    Car selectCarsByUserId(Long uid);

    int updateCarByPrimaryKeySelective(Car record);

    int updateCarByPrimaryKey(Car record);
}
