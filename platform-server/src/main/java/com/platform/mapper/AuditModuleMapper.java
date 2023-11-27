package com.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.platform.dto.auditModule.AuditModuleQueryPageDTO;
import com.platform.entity.AuditModule;
import com.platform.vo.auditModule.AuditModuleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuditModuleMapper extends BaseMapper<AuditModule> {
    Page<AuditModuleVO> queryPage(AuditModuleQueryPageDTO queryPageDTO);

    void updateAuditModule(@Param("et") AuditModule newAM);
}
