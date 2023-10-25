package com.platform.mapper;

import com.platform.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User getByUsername(String username);

}
