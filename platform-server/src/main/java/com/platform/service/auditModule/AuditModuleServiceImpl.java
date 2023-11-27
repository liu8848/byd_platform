package com.platform.service.auditModule;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.platform.annotaionExtend.FieldNameFill;
import com.platform.dto.auditModule.AuditModuleCreateOrUpdateDTO;
import com.platform.dto.auditModule.AuditModuleQueryPageDTO;
import com.platform.entity.AuditModule;
import com.platform.enums.FieldType;
import com.platform.exception.BaseException;
import com.platform.mapper.AuditModuleMapper;
import com.platform.result.PageResult;
import com.platform.result.UpdateResult;
import com.platform.vo.auditModule.AuditModuleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AuditModuleServiceImpl implements AuditModuleService{
    @Autowired
    private AuditModuleMapper auditModuleMapper;


    @Override
    public AuditModule createAuditModule(AuditModuleCreateOrUpdateDTO createDTO) {

        //准备数据
        AuditModule auditModule = new AuditModule();
        auditModule.setModuleName(createDTO.getModuleName());
        //检查是否已存在同名审核模块
        Long count = auditModuleMapper.selectCount(new QueryWrapper<AuditModule>().eq("module_name", createDTO.getModuleName()));
        if(count>0){
            throw new BaseException("审核模块:{"+createDTO.getModuleName()+"} 已存在。");
        }

        auditModuleMapper.insert(auditModule);

        return auditModule;
    }

    @Override
    @FieldNameFill(value = FieldType.USER)
    public AuditModuleVO getAuditModuleById(Long id) {
        AuditModule auditModule = auditModuleMapper.selectById(id);
        AuditModuleVO vo = new AuditModuleVO();
        BeanUtils.copyProperties(auditModule,vo);
        return vo;
    }

    @Override
    public PageResult<AuditModuleVO> getAuditModuleQueryPage(AuditModuleQueryPageDTO queryPageDTO) {
        PageHelper.startPage(queryPageDTO.getPage(), queryPageDTO.getSize());
        Page<AuditModuleVO> pageResult=auditModuleMapper.queryPage(queryPageDTO);
        long total = pageResult.getTotal();
        List<AuditModuleVO> result = pageResult.getResult();

        return new PageResult<>(total,result);
    }

    @Override
    public UpdateResult<AuditModule> updateAuditModule(AuditModuleCreateOrUpdateDTO updateDTO) {
        //获取原审核模块信息
        AuditModule oldAM = auditModuleMapper.selectById(updateDTO.getId());
        if(oldAM==null){
            throw new BaseException("审核模块不存在");
        }
        AuditModule newAM = new AuditModule();
        BeanUtils.copyProperties(updateDTO,newAM);

        auditModuleMapper.updateById(newAM);
        return UpdateResult.getUpdateContent(newAM, oldAM);
    }

    @Override
    public AuditModule deleteAuditModule(Long id) {
        //获取原审核模块信息
        AuditModule auditModule = auditModuleMapper.selectById(id);
        if(auditModule==null){
            throw new BaseException("审核模块不存在");
        }
        auditModuleMapper.deleteById(id);
        return auditModule;
    }
}
