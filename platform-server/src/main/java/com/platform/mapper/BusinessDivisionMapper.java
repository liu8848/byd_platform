package com.platform.mapper;

import com.github.pagehelper.Page;
import com.platform.dto.businessdivisions.BusinessDivisionPageQueryDTO;
import com.platform.entity.BusinessDivision;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessDivisionMapper {

    void add(BusinessDivision businessDivision);

    BusinessDivision selectById(Long buId);

    Page<BusinessDivision> getBUPageByQuery(BusinessDivisionPageQueryDTO businessDivisionPageQueryDTO);

    void deleteByBuId(long buId);

    void update(BusinessDivision businessDivision);
}
