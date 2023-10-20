package com.platform.service;

import com.platform.dto.BusinessDivisionCreateDTO;
import com.platform.dto.BusinessDivisionPageQueryDTO;
import com.platform.entity.BusinessDivision;
import com.platform.result.PageResult;

public interface BusinessDivisionService {
    void add(BusinessDivisionCreateDTO businessDivisionCreateDTO);

    BusinessDivision getByBuId(Long buId);

    PageResult<BusinessDivision> getBUPageByQuery(BusinessDivisionPageQueryDTO businessDivisionPageQueryDTO);

    void deleteByBuId(long buId);

    void updateBU(BusinessDivision businessDivision);
}
