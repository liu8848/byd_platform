package com.platform.mapper;

import com.github.pagehelper.Page;
import com.platform.dto.ProfessionInspectionPageQueryDTO;
import com.platform.entity.ProfessionInspection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProfessionInspectionMapper {

    ProfessionInspection getById(Long id);

    Page<ProfessionInspection> getPageByQuery(ProfessionInspectionPageQueryDTO queryDTO);

    void insert(ProfessionInspection professionInspection);

    void update(ProfessionInspection newDTO);

    int delete(Long id);

    void insertByCollection(List<ProfessionInspection> professionInspectionList);
}
