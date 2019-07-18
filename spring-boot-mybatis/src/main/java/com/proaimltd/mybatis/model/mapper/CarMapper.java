package com.proaimltd.mybatis.model.mapper;

import com.proaimltd.mybatis.model.entity.Car;

public interface CarMapper {
    int deleteCarByPrimaryKey(Long id);

    int deleteCarByUid(Long uid);

    int insertCar(Car record);

    int insertCarSelective(Car record);

    Car selectCarByPrimaryKey(Long id);

    Car selectCarsByUserId(Long uid);

    int updateCarByPrimaryKeySelective(Car record);

    int updateCarByPrimaryKey(Car record);
}
