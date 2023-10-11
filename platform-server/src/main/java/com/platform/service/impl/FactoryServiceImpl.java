package com.platform.service.impl;

import com.platform.entity.Factory;
import com.platform.mapper.FactoryMapper;
import com.platform.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactoryServiceImpl implements FactoryService {
    @Autowired
    private FactoryMapper factoryMapper;

    @Override
    public long countFactoryByBuId(Long buId) {
        return factoryMapper.countFactoryByBuId(buId);
    }

    @Override
    public Factory getFactoryById(Long id) {
        return factoryMapper.getFactoryById(id);
    }
}
