package cn.myst.web.jaeger.service.impl;

import cn.myst.web.jaeger.mapper.EmployeeMapper;
import cn.myst.web.jaeger.model.employee.Employee;
import cn.myst.web.jaeger.service.IEmployeeService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static cn.myst.web.jaeger.common.constant.BaseConstant.EMPLOYEE_CACHE_NAME;
import static cn.myst.web.jaeger.common.constant.BaseConstant.LISTS_PAGE_SIZE;


/**
 * @author ziming.xing
 * Create Time：2020/6/15
 */
@CacheConfig(cacheNames = {EMPLOYEE_CACHE_NAME})
@Service
@RequiredArgsConstructor
public class IEmployeeServiceImpl implements IEmployeeService {

    private final EmployeeMapper employeeMapper;

    @Override
    @CachePut(key = "'Employee[' + #result.id + ']'", unless = "#result == null")
    public Employee insertSelective(Employee record) {
        if (Objects.nonNull(record)) {
            employeeMapper.insertSelective(record);
            return record;
        }
        return null;
    }

    @Override
    // @CacheAble执行机制：1、调用目标方法前，根据Key检查是否已经有缓存数据；2、有缓存数据直接返回缓存数据，否则调用方法
    @Cacheable(keyGenerator = "keyGenerator", unless = "#result == null")
    public Employee selectByPrimaryKey(Integer id) {
        if (Objects.nonNull(id)) {
            return employeeMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    @Override
    // @CachePut执行机制：1、先调用目标方法；2、将目标方法的结果缓存起来
    // 注：@CacheAble无法使用#result，因为它的执行机制是在调用目标方法前（没有返回结果）；@CachePut的Key要与@CacheAble缓存的Key保持一致
    @CachePut(key = "'Employee[' + #result.id + ']'", condition = "#record.id != null", unless = "#result == null")
    public Employee updateByPrimaryKeySelective(Employee record) {
        if (Objects.nonNull(record) && record.getId() != 0) {
            if (employeeMapper.updateByPrimaryKeySelective(record) > 0) {
                return employeeMapper.selectByPrimaryKey(record.getId());
            }
        }
        return null;
    }

    @Override
    public List<Employee> selectByAll(Employee employee) {
        return employeeMapper.selectByAll(employee);
    }

    @Override
    @CacheEvict(allEntries = true)
    public int deleteByIdIn(List<Integer> ids) {
        AtomicInteger atomicInteger = new AtomicInteger();
        if (!CollectionUtils.isEmpty(ids)) {
            List<List<Integer>> lists = Lists.partition(ids, LISTS_PAGE_SIZE);
            for (List<Integer> list : lists) {
                atomicInteger.getAndAdd(employeeMapper.deleteByIdIn(list));
            }
        }
        return atomicInteger.get();
    }

    @Override
    @CacheEvict(key = "'Employee[' + #id + ']'", condition = "#id!=null")
    public int deleteByPrimaryKey(Integer id) {
        if (Objects.nonNull(id)) {
            return employeeMapper.deleteByPrimaryKey(id);
        }
        return 0;
    }

    @Override
    public int deleteByIds(List<Integer> ids) {
        AtomicInteger atomicInteger = new AtomicInteger();
        for (Integer id : ids) {
            atomicInteger.getAndAdd(this.deleteByPrimaryKey(id));
        }
        return atomicInteger.get();
    }

    @Override
    public int updateBatchSelective(List<Employee> list) {
        AtomicInteger atomicInteger = new AtomicInteger();
        if (!CollectionUtils.isEmpty(list)) {
            List<List<Employee>> lists = Lists.partition(list, LISTS_PAGE_SIZE);
            for (List<Employee> employees : lists) {
                atomicInteger.getAndAdd(employeeMapper.updateBatchSelective(employees));
            }
        }
        return atomicInteger.get();
    }

    @Override
    public int batchInsert(List<Employee> list) {
        AtomicInteger atomicInteger = new AtomicInteger();
        if (!CollectionUtils.isEmpty(list)) {
            List<List<Employee>> lists = Lists.partition(list, LISTS_PAGE_SIZE);
            for (List<Employee> employees : lists) {
                atomicInteger.getAndAdd(employeeMapper.batchInsert(employees));
            }
        }
        return atomicInteger.get();
    }

    @Override
    public Boolean checkByAll(Employee employee) {
        if (Objects.nonNull(employee)) {
            return employeeMapper.countByAll(employee) <= 0;
        }
        return false;
    }
}
