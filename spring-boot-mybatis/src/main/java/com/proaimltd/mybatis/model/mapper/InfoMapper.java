package com.proaimltd.mybatis.model.mapper;

import com.proaimltd.mybatis.model.entity.Info;

public interface InfoMapper {
    int deleteInfoByPrimaryKey(Long id);

    int insertInfo(Info record);

    int insertInfoSelective(Info record);

    Info selectInfoByUserId(Long id);

    int updateInfoByPrimaryKeySelective(Info record);

    int updateInfoByPrimaryKey(Info record);
}
