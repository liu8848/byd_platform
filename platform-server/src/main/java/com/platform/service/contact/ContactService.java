package com.platform.service.contact;

import com.platform.dto.FactoryContact.FactoryContactCreateDTO;
import com.platform.dto.FactoryContact.FactoryContactQueryPageDTO;
import com.platform.dto.FactoryContact.FactoryContactUpdateDTO;
import com.platform.entity.FactoryContact;
import com.platform.result.PageResult;
import com.platform.result.UpdateResult;
import com.platform.vo.factoryContact.FactoryContactVO;

import java.util.List;

public interface ContactService {
    FactoryContact createFactoryContact(FactoryContactCreateDTO createDTO);

    List<FactoryContactVO> getFactoryContact();

    UpdateResult<FactoryContactUpdateDTO> updateFactoryContact(FactoryContactUpdateDTO updateDTO);

    int deleteFactoryContent(Long employeeId);

    PageResult<FactoryContactVO> getFactoryContactQueryPage(FactoryContactQueryPageDTO queryPageDTO);

    List<FactoryContact> createFactoryContactByCollection(List<FactoryContactCreateDTO> createDTOS);
}
