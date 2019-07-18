package com.proaimltd.mybatis.service.impl;

import com.proaimltd.mybatis.model.entity.Info;
import com.proaimltd.mybatis.model.mapper.InfoMapper;
import com.proaimltd.mybatis.service.IInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.proaimltd.mybatis.common.enums.EnumInfo.FAILED_FIND_INFO_BY_ID;

/**
 * @author: ziming.xing
 * @date: 2019/7/12 13:48
 */
@Slf4j
@Service
public class InfoServiceImpl implements IInfoService {
    @Autowired
    private InfoMapper infoMapper;

    @Override
    public int deleteInfoByPrimaryKey(Long id) {
        Info info = infoMapper.selectInfoByUserId(id);
        if (null == info) {
            log.info(FAILED_FIND_INFO_BY_ID.getMessage());
        }
        return infoMapper.deleteInfoByPrimaryKey(id);
    }

    @Override
    public int insertInfo(Info record) {
        return 0;
    }

    @Override
    public int insertInfoSelective(Info record) {
        return infoMapper.insertInfoSelective(record);
    }

    @Override
    public Info selectInfoByUserId(Long id) {
        return infoMapper.selectInfoByUserId(id);
    }

    @Override
    public int updateInfoByPrimaryKeySelective(Info record) {
        Info info = infoMapper.selectInfoByUserId(record.getId());
        if (null == info) {
            log.info(FAILED_FIND_INFO_BY_ID.getMessage());
        }
        return infoMapper.updateInfoByPrimaryKeySelective(record);
    }

    @Override
    public int updateInfoByPrimaryKey(Info record) {
        return 0;
    }
}
