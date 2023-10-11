package com.platform.mapper;

import com.platform.entity.User;
import com.platform.vo.EmployeeLoginVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User getByUsername(String username);

}
