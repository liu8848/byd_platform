package com.platform.service;

import com.platform.dto.FactoryPageQueryDTO;
import com.platform.entity.Factory;
import com.platform.result.PageResult;
import com.platform.vo.FactoryDisplayVO;

public interface FactoryService {

    long countFactoryByBuId(Long buId);

    Factory getFactoryById(Long id);

    PageResult<FactoryDisplayVO> getFactoryPageByQuery(FactoryPageQueryDTO factoryPageQueryDTO);
}
