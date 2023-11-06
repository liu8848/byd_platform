package com.platform.mapper;

import com.github.pagehelper.Page;
import com.platform.dto.factories.FactoryPageQueryDTO;
import com.platform.entity.Factory;
import com.platform.vo.FactoryDisplayVO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface FactoryMapper {

    long countFactoryByBuId(long buId);

    Factory getFactoryById(long id);

    Page<FactoryDisplayVO> getFactoryPageByQuery(FactoryPageQueryDTO factoryPageQueryDTO);

    void addFactory(Factory factory);

    long deleteFactoryById(Long id);

    void addFactoryByCollection(List<Factory> factories);

    void update(Factory factory);

    void updateWarnTime(LocalDateTime time, Long factoryId);
}
