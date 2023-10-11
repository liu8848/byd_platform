package com.platform.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.platform.dto.FactoryPageQueryDTO;
import com.platform.entity.Factory;
import com.platform.mapper.FactoryMapper;
import com.platform.result.PageResult;
import com.platform.service.FactoryService;
import com.platform.vo.FactoryDisplayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public PageResult<FactoryDisplayVO> getFactoryPageByQuery(FactoryPageQueryDTO factoryPageQueryDTO) {
        PageHelper.startPage(factoryPageQueryDTO.getPage(),factoryPageQueryDTO.getSize());
        Page<FactoryDisplayVO> page = factoryMapper.getFactoryPageByQuery(factoryPageQueryDTO);
        long total=page.getTotal();
        List<FactoryDisplayVO> records=page.getResult();
        return new PageResult<>(total,records);
    }
}
