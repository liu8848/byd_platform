package com.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.platform.annotaionExtend.AutoFill;
import com.platform.dto.auditPlan.AuditPlanPageQueryDTO;
import com.platform.entity.AuditPlan;
import com.platform.enums.OperationType;
import com.platform.vo.AuditorDisplayVO;
import com.platform.vo.auditPlan.AuditPlanDisplayVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuditPlanMapper extends BaseMapper<AuditPlan> {

    @AutoFill(value = OperationType.INSERT)
    void create(AuditPlan auditPlan);

    Page<AuditPlanDisplayVO> queryPageAuditPlan(AuditPlanPageQueryDTO pageQueryDTO);

    @AutoFill(value = OperationType.UPDATE)
    void updateAuditPlan(AuditPlan newA);
}
