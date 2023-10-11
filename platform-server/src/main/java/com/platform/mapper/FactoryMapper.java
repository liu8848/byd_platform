package com.platform.mapper;

import com.platform.entity.Factory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FactoryMapper {

    long countFactoryByBuId(long buId);

    Factory getFactoryById(long id);
}
