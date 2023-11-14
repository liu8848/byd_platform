package com.platform.service.employee;

import com.platform.annotaionExtend.DictHelper;
import com.platform.annotaionExtend.DictParam;
import com.platform.constant.MessageConstant;
import com.platform.constant.RedisKeyConstant;
import com.platform.dto.employees.EmployeeCreateDTO;
import com.platform.dto.employees.EmployeeUpdateDTO;
import com.platform.entity.Auditor;
import com.platform.entity.AuditorStandingBookChange;
import com.platform.entity.Employee;
import com.platform.exception.BaseException;
import com.platform.mapper.AuditorMapper;
import com.platform.mapper.AuditorStandingBookChangeMapper;
import com.platform.mapper.EmployeeMapper;
import com.platform.result.UpdateResult;
import com.platform.utils.TransUtil;
import com.platform.vo.EmployeeDisplayVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private TransUtil transUtil;
    @Autowired
    private AuditorStandingBookChangeMapper auditorStandingBookChangeMapper;
    @Autowired
    private AuditorMapper auditorMapper;

    @Override
    public Employee insert(EmployeeCreateDTO createDTO) {
        Employee employee = Employee.builder()
                .name(createDTO.getName())
                .grade(createDTO.getGrade())
                .gender(createDTO.getGender())
                .education(createDTO.getEducation())
                .email(createDTO.getEmail())
                .factoryId(createDTO.getFactoryId())
                .departmentId(createDTO.getDepartmentId())
                .locationId(createDTO.getLocationId())
                .workStatus(createDTO.getWorkStatus())
                .build();

        employeeMapper.insert(employee);
        return employee;
    }

    @Override
    @DictHelper(value = {
            @DictParam(targetField = "factoryName", field = "factoryId", dictType = RedisKeyConstant.FACTORY)
    })
    public EmployeeDisplayVo getById(Long id) {
        Employee employee = employeeMapper.getById(id);
        return transUtil.employeeToVO(employee);
    }

    @Override
    @Transactional
    public List<Employee> importEmployee(List<EmployeeCreateDTO> createDTOS) {
        List<Employee> employees = transUtil.employeesCreateListToEmployeeList(createDTOS);
        employeeMapper.importEmployee(employees);
        return employees;
    }

    @Override
    @Transactional
    public UpdateResult<Employee> updateEmployee(EmployeeUpdateDTO createDTO) {
        //查询原员工信息
        Employee old_e = employeeMapper.getById(createDTO.getId());
        if(old_e==null){
            throw new BaseException("员工不存在");
        }

        //创建修改员工信息
        Employee update_e = new Employee();
        BeanUtils.copyProperties(createDTO,update_e);

        //员工事业部发生变动，触发事业部变动审核员台账更新
        if (update_e.getBuId()!=0&&!old_e.getBuId().equals(update_e.getBuId())){
            //查询该员工是否为审核员
            Auditor auditor = auditorMapper.getAuditorByEmployeeId(update_e.getId());
            //员工为审核员时触发事件
            if(auditor!=null){
                AuditorStandingBookChange auditorStandingBookChange = new AuditorStandingBookChange();
                auditorStandingBookChange.setEmployeeId(update_e.getId());
                auditorStandingBookChange.setOldBuId(old_e.getBuId());
                auditorStandingBookChange.setNowBuId(update_e.getBuId());
                auditorStandingBookChange.setOldFactoryId(old_e.getFactoryId());
                auditorStandingBookChange.setNowFactoryId(update_e.getFactoryId());

                //插入事业部变动审核员台账，并将员工任职状态变更为变动
                auditorStandingBookChangeMapper.insertStandingBookChange(auditorStandingBookChange);
                update_e.setWorkStatus(2);
            }
        }

        //执行更新
        employeeMapper.updateEmployee(update_e);

        UpdateResult<Employee> updateContent;
        updateContent=UpdateResult.getUpdateContent(update_e, old_e);
        return updateContent;
    }
}
