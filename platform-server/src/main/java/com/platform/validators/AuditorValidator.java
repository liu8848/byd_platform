package com.platform.validators;

import com.platform.constant.DictKeyConstant;
import com.platform.dto.auditors.AuditorCreateDTO;
import com.platform.entity.Auditor;
import com.platform.entity.Employee;
import com.platform.mapper.AuditorMapper;
import com.platform.mapper.EmployeeMapper;
import com.platform.utils.DictUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuditorValidator {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private AuditorMapper auditorMapper;

    public List<String> auditorCreateValidate(AuditorCreateDTO auditorCreateDTO){
        List<String> msg=new ArrayList<>();
        //检验工号是否存在
        Employee emp = employeeMapper.getById(auditorCreateDTO.getEmployeeId());
        if(emp==null){
            msg.add("员工：{"+auditorCreateDTO.getEmployeeId()+"}  不存在");
        }

        //检查该审核员是否已添加
        Auditor auditor = auditorMapper.getAuditorByEmployeeId(auditorCreateDTO.getEmployeeId());
        if(auditor!=null){
            msg.add("员工：{"+auditorCreateDTO.getEmployeeId()+"}  已添加为审核员");
        }

        //检查备案工厂是否存在
        if(!DictUtil.dictMap.get(DictKeyConstant.FACTORY).containsKey(Long.toString(auditorCreateDTO.getRecordFactoryId()))){
            msg.add("备案工厂:{"+auditorCreateDTO.getRecordFactoryId()+"} 不存在");
        }

        //检查学历是否合法
        if (!DictUtil.dictMap.get(DictKeyConstant.EDUCATION).containsKey(Integer.toString(auditorCreateDTO.getEducation()))){
            msg.add("学历不合法");
        }
        //检查审核员等级是否合法
        if(!DictUtil.dictMap.get(DictKeyConstant.AUDITOR_LEVEL).containsKey(Integer.toString(auditorCreateDTO.getAuditorLevel()))){
            msg.add("审核员等级不合法");
        }
        return msg;
    }

}
