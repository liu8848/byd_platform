package com.platform.service.contact;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.platform.annotaionExtend.DictHelper;
import com.platform.annotaionExtend.DictParam;
import com.platform.constant.DictKeyConstant;
import com.platform.constant.MessageConstant;
import com.platform.dto.FactoryContact.FactoryContactCreateDTO;
import com.platform.entity.Factory;
import com.platform.entity.FactoryContact;
import com.platform.exception.BaseException;
import com.platform.exception.FactoryNotExistException;
import com.platform.mapper.FactoryContactMapper;
import com.platform.mapper.FactoryMapper;
import com.platform.vo.factoryContact.FactoryContactVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService{
    @Autowired
    private FactoryContactMapper factoryContactMapper;
    @Autowired
    private FactoryMapper factoryMapper;

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
}
