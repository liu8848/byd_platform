package com.platform.mapper;

import com.platform.annotaionExtend.AutoFill;
import com.platform.entity.Auditor;
import com.platform.enums.OperationType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuditorMapper {
    List<Auditor> getAuditor();

    @AutoFill(value = OperationType.INSERT)
    void insert(Auditor auditor);
}
