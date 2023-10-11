package com.platform.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.platform.context.BaseContext;
import com.platform.dto.BusinessDivisionCreateDTO;
import com.platform.dto.BusinessDivisionPageQueryDTO;
import com.platform.entity.BusinessDivision;
import com.platform.mapper.BusinessDivisionMapper;
import com.platform.result.PageResult;
import com.platform.service.BusinessDivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BusinessDivisionServiceImpl implements BusinessDivisionService {

    @Autowired
    private BusinessDivisionMapper businessDivisionMapper;

    @Override
    public void add(BusinessDivisionCreateDTO businessDivisionCreateDTO) {

        BusinessDivision businessDivision = BusinessDivision.builder()
                .buName(businessDivisionCreateDTO.getBu_name())
                .createTime(LocalDateTime.now())
                .createUser(BaseContext.getCurrentId().getEmployeeId())
                .build();

        businessDivisionMapper.add(businessDivision);
    }

    @Override
    public BusinessDivision getByBuId(Long buId) {
        return businessDivisionMapper.selectById(buId);
    }

    @Override
    public PageResult<BusinessDivision> getBUPageByQuery(BusinessDivisionPageQueryDTO businessDivisionPageQueryDTO) {
        PageHelper.startPage(businessDivisionPageQueryDTO.getPage(),businessDivisionPageQueryDTO.getSize());
        Page<BusinessDivision> page=businessDivisionMapper.getBUPageByQuery(businessDivisionPageQueryDTO);
        long total=page.getTotal();
        List<BusinessDivision> buList=page.getResult();
        return new PageResult<>(total,buList);
    }

    @Override
    public void deleteByBuId(long buId) {
        businessDivisionMapper.deleteByBuId(buId);
    }

    @Override
    public void updateBU(BusinessDivision businessDivision) {
        businessDivision.setUpdateTime(LocalDateTime.now());
        businessDivision.setUpdateUser(BaseContext.getCurrentId().getEmployeeId());
        businessDivisionMapper.update(businessDivision);
    }
}
