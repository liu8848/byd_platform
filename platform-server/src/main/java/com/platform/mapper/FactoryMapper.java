package com.platform.mapper;

import com.github.pagehelper.Page;
import com.platform.dto.FactoryPageQueryDTO;
import com.platform.entity.Factory;
import com.platform.vo.FactoryDisplayVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FactoryMapper {

    long countFactoryByBuId(long buId);

    Factory getFactoryById(long id);

    Page<FactoryDisplayVO> getFactoryPageByQuery(FactoryPageQueryDTO factoryPageQueryDTO);
}
