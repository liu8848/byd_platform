package com.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.annotaionExtend.AutoFill;
import com.platform.entity.Employee;
import com.platform.enums.OperationType;
import com.platform.vo.EmployeeDisplayVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

    List<EmployeeDisplayVo> getAll();

    Employee getById(Long id);

    @AutoFill(value = OperationType.INSERT)
    int insert(Employee employee);

    @AutoFill(value = OperationType.INSERT)
    void importEmployee(List<Employee> employees);

    @AutoFill(value = OperationType.UPDATE)
    void updateEmployee(Employee updateE);
}
