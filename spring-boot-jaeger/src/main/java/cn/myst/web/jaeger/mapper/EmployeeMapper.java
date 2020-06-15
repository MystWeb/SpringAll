package cn.myst.web.jaeger.mapper;

import cn.myst.web.jaeger.model.employee.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * @author ziming.xing
 * Create Time：2020/6/15
 */
@Mapper
public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> selectByAll(Employee employee);

    int deleteByIdIn(@Param("idCollection") Collection<Integer> idCollection);

    int deleteByDIdIn(@Param("dIdCollection") Collection<Integer> dIdCollection);

    int updateBatchSelective(List<Employee> list);

    int batchInsert(@Param("list") List<Employee> list);

    int countByAll(Employee employee);
}