package com.platform.service;

import com.platform.dto.auditors.AuditorCreateDTO;
import com.platform.dto.auditors.AuditorPageQueryDTO;
import com.platform.entity.Auditor;
import com.platform.entity.AuditorStandingBookInWork;
import com.platform.result.PageResult;
import com.platform.vo.AuditorDisplayVO;
import com.platform.vo.AuditorStandingBookInWorkVO;
import com.platform.vo.OnWorkAuditorDisplayVO;

import java.util.List;

public interface AuditorService {
//    List<OnWorkAuditorDisplayVO> getOnWorkAuditor() throws NoSuchFieldException;

    Auditor insert(AuditorCreateDTO auditorCreateDTO);

    void updateOnWorkStandingBook(Long recordFactoryId);

    List<AuditorStandingBookInWorkVO> getStandingBookInWork();

    PageResult<AuditorStandingBookInWork> getPageQueryStandingBookInWork(AuditorPageQueryDTO pageQueryDTO);

    List<AuditorDisplayVO> getAuditorAll();

    AuditorDisplayVO getAuditorByEmployeeId(Long employeeId);

    void deleteAuditorByEmployeeId(Long employeeId);

    void updateAuditorArrangement(Long employeeId, Boolean isArrange);
}
