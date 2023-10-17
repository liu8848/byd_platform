package com.platform.service;

import com.platform.dto.FactoryCreateDTO;
import com.platform.dto.FactoryPageQueryDTO;
import com.platform.entity.Factory;
import com.platform.result.PageResult;
import com.platform.result.UpdateResult;
import com.platform.vo.FactoryDisplayVO;

import java.util.List;

public interface FactoryService {

    long countFactoryByBuId(Long buId);

    Factory getFactoryById(Long id);

    PageResult<FactoryDisplayVO> getFactoryPageByQuery(FactoryPageQueryDTO factoryPageQueryDTO);

    Factory addFactory(FactoryCreateDTO factoryCreateDTO);

    long deleteFactoryById(Long id);

    List<Factory> addFactoryByCollection(List<FactoryCreateDTO> factories);

    UpdateResult<Factory> updateFactory(Factory factory) throws IllegalAccessException;
}
