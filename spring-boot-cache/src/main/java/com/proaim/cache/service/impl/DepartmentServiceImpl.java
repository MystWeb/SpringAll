package com.proaim.cache.service.impl;

import com.proaim.cache.entity.Department;
import com.proaim.cache.mapper.DepartmentMapper;
import com.proaim.cache.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
@CacheConfig(cacheNames = "dept")
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Cacheable
    @Override
    public Department getDeptById(Integer id) {
        log.info("查询部门ID：" + id);
        Department deptById = departmentMapper.getDeptById(id);

        // 获取某个缓存
        Object dept = redisTemplate.opsForValue().get("dept");
        

        return deptById;
    }
}
