package com.platform.service.auditModule;

import com.platform.dto.auditModule.AuditModuleCreateOrUpdateDTO;
import com.platform.dto.auditModule.AuditModuleQueryPageDTO;
import com.platform.entity.AuditModule;
import com.platform.result.PageResult;
import com.platform.result.UpdateResult;
import com.platform.vo.auditModule.AuditModuleVO;

public interface AuditModuleService {
    AuditModule createAuditModule(AuditModuleCreateOrUpdateDTO createDTO);

    AuditModuleVO getAuditModuleById(Long id);

    PageResult<AuditModuleVO> getAuditModuleQueryPage(AuditModuleQueryPageDTO queryPageDTO);

    UpdateResult<AuditModule> updateAuditModule(AuditModuleCreateOrUpdateDTO updateDTO);

    AuditModule deleteAuditModule(Long id);
}
