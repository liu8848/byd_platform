package com.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.platform.annotaionExtend.AutoFill;
import com.platform.dto.auditors.AuditorStandingBookChangePageQueryDTO;
import com.platform.entity.AuditorStandingBookChange;
import com.platform.enums.OperationType;
import com.platform.vo.AuditorStandingBookChangeDisplayVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuditorStandingBookChangeMapper extends BaseMapper<AuditorStandingBookChange> {

    @AutoFill(OperationType.INSERT)
    void insertStandingBookChange(AuditorStandingBookChange auditorStandingBookChange);

    List<AuditorStandingBookChangeDisplayVO> getStandingBookChangeList();

    Page<AuditorStandingBookChangeDisplayVO> queryPageAuditorStandingBookChange(AuditorStandingBookChangePageQueryDTO pageQueryDTO);
}
