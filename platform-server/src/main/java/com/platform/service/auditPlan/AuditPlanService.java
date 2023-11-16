package com.platform.service.auditPlan;

import com.platform.entity.AuditPlan;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface AuditPlanService {
    AuditPlan createAuditPlan(Map<String, List<MultipartFile>> fileMap, AuditPlan auditPlan);
}
