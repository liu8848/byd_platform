package com.platform.service;

import com.platform.entity.Factory;

public interface FactoryService {

    long countFactoryByBuId(Long buId);

    Factory getFactoryById(Long id);
}
