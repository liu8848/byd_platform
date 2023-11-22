package com.platform.service.auditPlan;

import com.platform.dto.auditPlan.AuditPlanCreateDTO;
import com.platform.dto.auditPlan.AuditPlanPageQueryDTO;
import com.platform.dto.auditPlan.AuditPlanUpdateDTO;
import com.platform.entity.AuditPlan;
import com.platform.result.PageResult;
import com.platform.result.Result;
import com.platform.result.UpdateResult;
import com.platform.vo.auditPlan.AuditPlanDisplayVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface AuditPlanService {
    AuditPlan createAuditPlan(Map<String, List<MultipartFile>> fileMap, AuditPlanCreateDTO createDTO) throws IOException;

    PageResult<AuditPlanDisplayVO> queryPage(AuditPlanPageQueryDTO pageQueryDTO);

    AuditPlanDisplayVO getById(Long id);

    void deleteById(Long id);

    UpdateResult<AuditPlan> updateAuditPlan(Long id, AuditPlanUpdateDTO updateDTO);
}
