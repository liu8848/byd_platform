package com.platform.service;

import com.platform.dto.professionInspections.ProfessionInspectionCreateDTO;
import com.platform.dto.professionInspections.ProfessionInspectionPageQueryDTO;
import com.platform.entity.ProfessionInspection;
import com.platform.result.PageResult;
import com.platform.result.UpdateResult;

import java.util.List;

public interface ProfessionInspectionService {
    ProfessionInspection getById(Long id);

    PageResult<ProfessionInspection> getPageByQuery(ProfessionInspectionPageQueryDTO queryDTO);

    ProfessionInspection insert(ProfessionInspectionCreateDTO createDTO);

    UpdateResult<ProfessionInspection> update(ProfessionInspection newDTO) throws IllegalAccessException;

    int delete(Long id);

    List<ProfessionInspection> insertByCollection(List<ProfessionInspectionCreateDTO> createDTOList);
}
