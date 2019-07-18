package com.proaimltd.mybatis.service;

import com.proaimltd.mybatis.model.entity.Info;

/**
 * @author: ziming.xing
 * @date: 2019/7/12 13:48
 */
public interface IInfoService {
    int deleteInfoByPrimaryKey(Long id);

    int insertInfo(Info record);

    int insertInfoSelective(Info record);

    Info selectInfoByUserId(Long id);

    int updateInfoByPrimaryKeySelective(Info record);

    int updateInfoByPrimaryKey(Info record);
}
