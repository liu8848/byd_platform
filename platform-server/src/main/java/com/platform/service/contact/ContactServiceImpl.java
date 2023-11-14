package com.platform.service.contact;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.platform.annotaionExtend.DictHelper;
import com.platform.annotaionExtend.DictParam;
import com.platform.constant.DictKeyConstant;
import com.platform.constant.MessageConstant;
import com.platform.dto.FactoryContact.FactoryContactCreateDTO;
import com.platform.dto.FactoryContact.FactoryContactQueryPageDTO;
import com.platform.dto.FactoryContact.FactoryContactUpdateDTO;
import com.platform.entity.Employee;
import com.platform.entity.Factory;
import com.platform.entity.FactoryContact;
import com.platform.exception.BaseException;
import com.platform.exception.FactoryNotExistException;
import com.platform.mapper.EmployeeMapper;
import com.platform.mapper.FactoryContactMapper;
import com.platform.mapper.FactoryMapper;
import com.platform.result.PageResult;
import com.platform.result.UpdateResult;
import com.platform.vo.factoryContact.FactoryContactVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService{
    @Autowired
    private FactoryContactMapper factoryContactMapper;
    @Autowired
    private FactoryMapper factoryMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public FactoryContact createFactoryContact(FactoryContactCreateDTO createDTO) {
        //检查是否存在该体系接口人
        QueryWrapper<FactoryContact> factoryContactQueryWrapper = new QueryWrapper<>();
        factoryContactQueryWrapper.eq("employee_id",createDTO.getEmployeeId());
        FactoryContact queryContact = factoryContactMapper.selectOne(factoryContactQueryWrapper);
        if(queryContact!=null){
            throw new BaseException("接口人已存在，无法创建相同接口人");
        }

        //检查工厂是否存在，与事业部是否匹配
        Factory factory = factoryMapper.getFactoryById(createDTO.getRecordFactoryId());
        if(factory==null){
            throw new FactoryNotExistException(MessageConstant.FACTORY_NOT_EXIST);
        }
        if(factory.getBu().getBuId()!= createDTO.getBuId()){
            throw new BaseException("备案工厂与事业部不相匹配");
        }

        FactoryContact factoryContact = new FactoryContact();
        BeanUtils.copyProperties(createDTO,factoryContact);
        factoryContactMapper.createFactoryContact(factoryContact);

        return factoryContact;
    }

    /***
     *
     * @return 工厂体系接口人列表
     */
    @Override
    @DictHelper(value = {
            @DictParam(field = "buId",targetField = "buName",dictType = DictKeyConstant.BUSINESSDIVISION),
            @DictParam(field = "recordFactoryId",targetField = "recordFactoryName",dictType = DictKeyConstant.FACTORY)
    })
    public List<FactoryContactVO> getFactoryContact() {
        List<FactoryContactVO> result= factoryContactMapper.getFactoryContact();
        return result;
    }

    @Override
    @Transactional
    public UpdateResult<FactoryContactUpdateDTO> updateFactoryContact(FactoryContactUpdateDTO updateDTO) {
        //获取原接口人信息
        FactoryContactVO oldContact = factoryContactMapper.getFactoryContactByEmployeeId(updateDTO.getEmployeeId());
        if(oldContact==null){
            throw new BaseException("不存在当前接口人，无法更新");
        }
        FactoryContactUpdateDTO old = new FactoryContactUpdateDTO();
        BeanUtils.copyProperties(oldContact,old);

        //检查备案工厂与事业部是否匹配
        if (updateDTO.getRecordFactoryId()!=null) {
            Factory factory = factoryMapper.getFactoryById(updateDTO.getRecordFactoryId());
            if (updateDTO.getBuId() != null && updateDTO.getBuId() != 0 && factory.getBu().getBuId() != updateDTO.getBuId()) {
                throw new BaseException("事业部与备案工厂不匹配");
            }
        }

        //创建更新对象
        FactoryContactUpdateDTO newContact = getFactoryContactUpdateDTO(updateDTO, oldContact);
        Employee empUpdate = new Employee();
        empUpdate.setId(updateDTO.getEmployeeId());
        empUpdate.setPhone(oldContact.getPhone()== updateDTO.getPhone()?null: updateDTO.getPhone());
        empUpdate.setEmail(oldContact.getEmail()== updateDTO.getEmail()?null: updateDTO.getEmail());

        //执行更新
        factoryContactMapper.updateFactoryContact(newContact);
        employeeMapper.updateEmployee(empUpdate);

        return UpdateResult.getUpdateContent(newContact,old);
    }

    /***
     *
     * @param employeeId 需要删除的员工工号
     * @return 删除条数
     */
    @Override
    public int deleteFactoryContent(Long employeeId) {
        QueryWrapper<FactoryContact> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("employee_id",employeeId);
        int row = factoryContactMapper.delete(queryWrapper);
        return row;
    }


    /***
     *
     * @param queryPageDTO 分页条件搜索数据模型
     * @return 分页搜索结果
     */
    @Override
    @DictHelper(value = {
            @DictParam(field = "buId",targetField = "buName",dictType = DictKeyConstant.BUSINESSDIVISION),
            @DictParam(field = "recordFactoryId",targetField = "recordFactoryName",dictType = DictKeyConstant.FACTORY)
    })
    public PageResult<FactoryContactVO> getFactoryContactQueryPage(FactoryContactQueryPageDTO queryPageDTO) {
        PageHelper.startPage(queryPageDTO.getPage(), queryPageDTO.getSize());
        Page<FactoryContactVO> pageResult=factoryContactMapper.getFactoryContactQueryPage(queryPageDTO);
        long total = pageResult.getTotal();
        List<FactoryContactVO> result = pageResult.getResult();
        return new PageResult<>(total,result);
    }


    /***
     *
     * @param updateDTO 更新数据模型
     * @param oldContact 原数据模型
     * @return 需更新的数据模型字段
     */
    private static FactoryContactUpdateDTO getFactoryContactUpdateDTO(FactoryContactUpdateDTO updateDTO, FactoryContactVO oldContact) {
        FactoryContactUpdateDTO newContact = new FactoryContactUpdateDTO();
        newContact.setEmployeeId(updateDTO.getEmployeeId());
        newContact.setBuId(oldContact.getBuId()== updateDTO.getBuId()?null: updateDTO.getBuId());
        newContact.setRecordFactoryId(oldContact.getRecordFactoryId()== updateDTO.getRecordFactoryId()?null: updateDTO.getRecordFactoryId());
        newContact.setPhone(oldContact.getPhone()== updateDTO.getPhone()?null: updateDTO.getPhone());
        newContact.setEmail(oldContact.getEmail()== updateDTO.getEmail()?null: updateDTO.getEmail());
        return newContact;
    }
}
