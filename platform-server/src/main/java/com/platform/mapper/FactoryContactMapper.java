package com.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.annotaionExtend.AutoFill;
import com.platform.entity.FactoryContact;
import com.platform.enums.OperationType;
import com.platform.vo.factoryContact.FactoryContactVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FactoryContactMapper extends BaseMapper<FactoryContact> {

    @AutoFill(value = OperationType.INSERT)
    void createFactoryContact(FactoryContact factoryContact);

    List<FactoryContactVO> getFactoryContact();
}
