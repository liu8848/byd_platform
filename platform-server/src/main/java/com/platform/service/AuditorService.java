package com.platform.service;

import com.platform.dto.auditors.AuditorCreateDTO;
import com.platform.entity.Auditor;
import com.platform.entity.AuditorStandingBookInWork;
import com.platform.vo.OnWorkAuditorDisplayVO;

import java.util.List;

public interface AuditorService {
    List<OnWorkAuditorDisplayVO> getOnWorkAuditor() throws NoSuchFieldException;

    Auditor insert(AuditorCreateDTO auditorCreateDTO);

    void updateOnWorkStandingBook(Long recordFactoryId);

    List<AuditorStandingBookInWork> getStandingBookInWork();

}
