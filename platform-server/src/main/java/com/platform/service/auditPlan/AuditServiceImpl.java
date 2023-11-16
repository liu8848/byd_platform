package com.platform.service.auditPlan;

import com.platform.entity.AuditPlan;
import com.platform.mapper.AuditPlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class AuditServiceImpl implements AuditPlanService{

    @Autowired
    private AuditPlanMapper auditPlanMapper;

    @Override
    public AuditPlan createAuditPlan(Map<String, List<MultipartFile>> fileMap, AuditPlan auditPlan) {
        return null;
    }
}
