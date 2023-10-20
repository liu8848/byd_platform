package com.platform.mapper;

import com.platform.entity.Auditor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuditorMapper {
    List<Auditor> getAuditor();

    void insert(Auditor auditor);
}
