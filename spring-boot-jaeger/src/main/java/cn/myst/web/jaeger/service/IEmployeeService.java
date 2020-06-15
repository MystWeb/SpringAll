package cn.myst.web.jaeger.service;


import cn.myst.web.jaeger.model.employee.Employee;

import java.util.List;

/**
 * @author ziming.xing
 * Create Time：2020/6/15
 */
public interface IEmployeeService {

    Employee insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    Employee updateByPrimaryKeySelective(Employee record);

    List<Employee> selectByAll(Employee employee);

    /**
     * 批量删除员工 + 清空缓存
     *
     * @param ids 员工ID集
     * @return 行数
     */
    int deleteByIdIn(List<Integer> ids);

    /**
     * 根据ID删除员工 + 缓存
     *
     * @param id 员工ID
     * @return 行数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 批量删除员工 + 缓存
     *
     * @param ids 员工ID集
     * @return 行数
     */
    int deleteByIds(List<Integer> ids);

    int updateBatchSelective(List<Employee> list);

    int batchInsert(List<Employee> list);

    /**
     * 校验员工重复
     *
     * @param employee Employee
     * @return true=可用，false=不可用
     */
    Boolean checkByAll(Employee employee);
}
