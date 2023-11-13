package com.platform.service.contact;

import com.platform.dto.FactoryContact.FactoryContactCreateDTO;
import com.platform.entity.FactoryContact;
import com.platform.vo.factoryContact.FactoryContactVO;

import java.util.List;

public interface ContactService {
    FactoryContact createFactoryContact(FactoryContactCreateDTO createDTO);

    List<FactoryContactVO> getFactoryContact();
}
