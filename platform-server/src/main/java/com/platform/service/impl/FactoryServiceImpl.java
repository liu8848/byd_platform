package com.platform.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.platform.constant.MessageConstant;
import com.platform.context.BaseContext;
import com.platform.dto.factories.FactoryCreateDTO;
import com.platform.dto.factories.FactoryPageQueryDTO;
import com.platform.entity.BusinessDivision;
import com.platform.entity.Factory;
import com.platform.exception.BusinessDivisionNotExistException;
import com.platform.exception.FactoryNotExistException;
import com.platform.mapper.BusinessDivisionMapper;
import com.platform.mapper.FactoryMapper;
import com.platform.result.PageResult;
import com.platform.result.UpdateResult;
import com.platform.service.FactoryService;
import com.platform.vo.FactoryDisplayVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FactoryServiceImpl implements FactoryService {
    @Autowired
    private FactoryMapper factoryMapper;

    @Autowired
    private BusinessDivisionMapper businessDivisionMapper;

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
        PageHelper.startPage(factoryPageQueryDTO.getPage(), factoryPageQueryDTO.getSize());
        Page<FactoryDisplayVO> page = factoryMapper.getFactoryPageByQuery(factoryPageQueryDTO);
        long total = page.getTotal();
        List<FactoryDisplayVO> records = page.getResult();
        return new PageResult<>(total, records);
    }

    @Override
    public Factory addFactory(FactoryCreateDTO factoryCreateDTO) {
        BusinessDivision businessDivision = businessDivisionMapper.selectById(factoryCreateDTO.getBuId());
        if (businessDivision == null) {
            throw new BusinessDivisionNotExistException("事业部编号:" + factoryCreateDTO.getBuId() + "不存在");
        }
        Factory factory = Factory.builder().name(factoryCreateDTO.getName())
                .level(factoryCreateDTO.getLevel())
                .buId(factoryCreateDTO.getBuId())
                .createTime(LocalDateTime.now())
                .createUser(BaseContext.getCurrentId().getEmployeeId()).build();
        factoryMapper.addFactory(factory);
        return factory;
    }

    @Override
    public long deleteFactoryById(Long id) {
        long row = factoryMapper.deleteFactoryById(id);
        if (row == 0) {
            throw new FactoryNotExistException(MessageConstant.FACTORY_NOT_EXIST);
        }
        return row;
    }

    @Override
    public List<Factory> addFactoryByCollection(List<FactoryCreateDTO> factories) {
        List<Factory> factoryList = new ArrayList<>();
        for (FactoryCreateDTO factoryCreateDTO : factories) {
            Factory factory = Factory.builder().name(factoryCreateDTO.getName())
                    .level(factoryCreateDTO.getLevel())
                    .buId(factoryCreateDTO.getBuId())
                    .createTime(LocalDateTime.now())
                    .createUser(BaseContext.getCurrentId().getEmployeeId()).build();
            factoryList.add(factory);
        }
        factoryMapper.addFactoryByCollection(factoryList);
        return factoryList;
    }

    @Override
    public UpdateResult<Factory> updateFactory(Factory factory) throws IllegalAccessException {
        Factory oldFactory = factoryMapper.getFactoryById(factory.getId());
        if (oldFactory == null) {
            throw new FactoryNotExistException(MessageConstant.FACTORY_NOT_EXIST);
        }
        factory.setUpdateTime(LocalDateTime.now());
        factory.setUpdateUser(BaseContext.getCurrentId().getEmployeeId());
        factoryMapper.update(factory);

        log.info("修改备案工厂信息: 旧：{}  -->  新：{}", oldFactory, factory);

        return UpdateResult.getUpdateContent(factory, oldFactory);
    }
}
