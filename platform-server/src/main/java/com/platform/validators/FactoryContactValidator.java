package com.platform.validators;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.platform.commonModel.Dictionary;
import com.platform.constant.DictKeyConstant;
import com.platform.dto.FactoryContact.FactoryContactCreateDTO;
import com.platform.entity.Employee;
import com.platform.mapper.EmployeeMapper;
import com.platform.utils.DictUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class FactoryContactValidator {
    @Autowired
    private EmployeeMapper employeeMapper;

    public List<String> isValid(FactoryContactCreateDTO createDTO){
        List<String> errMsg=new ArrayList<>();

        //查询员工是否存在
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",createDTO.getEmployeeId());
        Employee e = employeeMapper.selectOne(queryWrapper);
        if(e==null){
            errMsg.add("工号:"+createDTO.getEmployeeId()+"不存在");
        }
        if (e==null|| !Objects.equals(e.getName(), createDTO.getEmployeeName())){
            errMsg.add("工号："+createDTO.getEmployeeId()+"与姓名:"+ createDTO.getEmployeeName()+"不匹配");
        }

        //事业部编号与名称是否匹配；
        Dictionary buDict = DictUtil.dictMap.get(DictKeyConstant.BUSINESSDIVISION).get(createDTO.getBuId());
        if(!Objects.equals(buDict.getDictName(), createDTO.getBuName())){
            errMsg.add("事业部："+createDTO.getBuName()+"与事业部编号："+createDTO.getBuId()+"不匹配");
        }

        return errMsg;
    }
}
