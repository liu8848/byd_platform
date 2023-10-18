package com.platform.mapper;

import com.platform.entity.Employee;
import com.platform.vo.EmployeeDisplayVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    List<EmployeeDisplayVo> getAll();
    Employee getById(Long id);
}
