package cn.myst.web.jaeger.controller;

import cn.myst.web.jaeger.common.page.BasePagingQuery;
import cn.myst.web.jaeger.common.utils.BeansUtils;
import cn.myst.web.jaeger.common.validation.ValidatableList;
import cn.myst.web.jaeger.model.employee.Employee;
import cn.myst.web.jaeger.model.employee.dto.EmployeeCheckReqDTO;
import cn.myst.web.jaeger.model.employee.dto.EmployeeCreateReqDTO;
import cn.myst.web.jaeger.model.employee.dto.EmployeeUpdateReqDTO;
import cn.myst.web.jaeger.service.IEmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static cn.myst.web.jaeger.config.SwaggerConfig.URL_PREFIX;
import static cn.myst.web.jaeger.config.SwaggerConfig.URL_VERSION;


/**
 * @author ziming.xing
 * Create Time：2020/6/15
 */
@Api(tags = "员工管理")
@RequestMapping(value = URL_PREFIX + URL_VERSION + "/employee")
@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final IEmployeeService employeeService;

    @PostMapping
    @ApiOperation("新建员工")
    public Employee createEmployee(@RequestBody EmployeeCreateReqDTO reqDTO) {
        if (Objects.nonNull(reqDTO)) {
            Employee employee = new Employee();
            BeanUtils.copyProperties(reqDTO, employee);
            return employeeService.insertSelective(employee);
        }
        return null;
    }

    @PostMapping("/batch")
    @ApiOperation("批量新建员工")
    public Integer createEmployees(@RequestBody List<EmployeeCreateReqDTO> reqDTOList) {
        if (!CollectionUtils.isEmpty(reqDTOList)) {
            // List复制
            List<Employee> departments = BeansUtils.copyListProperties(reqDTOList, Employee::new);
            return employeeService.batchInsert(departments);
        }
        return null;
    }

    @PutMapping
    @ApiOperation("更新员工")
    public Employee updateEmployee(@RequestBody EmployeeUpdateReqDTO reqDTO) {
        if (Objects.nonNull(reqDTO)) {
            Employee employee = new Employee();
            BeanUtils.copyProperties(reqDTO, employee);
            return employeeService.updateByPrimaryKeySelective(employee);
        }
        return null;
    }

    @PutMapping("/batch")
    @ApiOperation("批量更新员工")
    public Integer updateEmployees(@Validated @RequestBody ValidatableList<EmployeeUpdateReqDTO> reqDTOList) {
        if (!CollectionUtils.isEmpty(reqDTOList)) {
            // List复制
            List<Employee> departments = BeansUtils.copyListProperties(reqDTOList, Employee::new);
            return employeeService.updateBatchSelective(departments);
        }
        return null;
    }

    @DeleteMapping("/batch")
    @ApiOperation("批量删除员工")
    public Integer deleteEmployees(@RequestBody List<Integer> ids) {
        return employeeService.deleteByIdIn(ids);
    }

    @GetMapping("/{id}")
    @ApiOperation("查询员工详情")
    @ApiImplicitParam(name = "id", value = "员工ID", required = true)
    public Employee getEmployee(@PathVariable Integer id) {
        return employeeService.selectByPrimaryKey(id);
    }

    @GetMapping("/page")
    @ApiOperation(value = "查询员工列表", notes = "分页查询，模糊查询，排序")
    public PageInfo<Employee> getEmployees(BasePagingQuery pagingQuery) {
        PageHelper.startPage(pagingQuery.getPageNum(), pagingQuery.getPageSize());
        Employee employee = new Employee();
        // 模糊查询
        employee.setKeyword(pagingQuery.getKeyword());
        // 排序
        employee.setSortKeys(pagingQuery.getSortKeys());
        return new PageInfo<>(employeeService.selectByAll(employee));
    }

    @GetMapping("/check")
    @ApiOperation(value = "校验员工", notes = "true=可用，false=不可用")
    public Boolean checkByAll(EmployeeCheckReqDTO checkReqDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(checkReqDTO, employee);
        return employeeService.checkByAll(employee);
    }

}
