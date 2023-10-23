package com.platform.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.platform.context.BaseContext;
import com.platform.dto.professionInspections.ProfessionInspectionCreateDTO;
import com.platform.dto.professionInspections.ProfessionInspectionPageQueryDTO;
import com.platform.entity.ProfessionInspection;
import com.platform.exception.BaseException;
import com.platform.mapper.ProfessionInspectionMapper;
import com.platform.result.PageResult;
import com.platform.result.UpdateResult;
import com.platform.service.ProfessionInspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessionInspectionImpl implements ProfessionInspectionService {
    @Autowired
    private ProfessionInspectionMapper professionInspectionMapper;

    @Override
    public ProfessionInspection getById(Long id) {
        return professionInspectionMapper.getById(id);
    }

    @Override
    public PageResult<ProfessionInspection> getPageByQuery(ProfessionInspectionPageQueryDTO queryDTO) {
        PageHelper.startPage(queryDTO.getPage(), queryDTO.getSize());
        Page<ProfessionInspection> page = professionInspectionMapper.getPageByQuery(queryDTO);
        long total = page.getTotal();
        List<ProfessionInspection> records = page.getResult();
        return new PageResult<>(total, records);
    }

    @Override
    public ProfessionInspection insert(ProfessionInspectionCreateDTO createDTO) {
        ProfessionInspection professionInspection = ProfessionInspection.builder()
                .name(createDTO.getName())
                .createTime(LocalDateTime.now())
                .createUserId(BaseContext.getCurrentId().getEmployeeId())
                .build();
        professionInspectionMapper.insert(professionInspection);
        return professionInspection;
    }

    @Override
    public UpdateResult<ProfessionInspection> update(ProfessionInspection newDTO) throws IllegalAccessException {
        ProfessionInspection oldDTO = professionInspectionMapper.getById(newDTO.getId());
        if (oldDTO == null) {
            throw new BaseException("专业检查不存在");
        }
        newDTO.setUpdateTime(LocalDateTime.now());
        newDTO.setUpdateUser(BaseContext.getCurrentId().getEmployeeId());
        professionInspectionMapper.update(newDTO);

        return UpdateResult.getUpdateContent(newDTO, oldDTO);
    }

    @Override
    public int delete(Long id) {
        int row = professionInspectionMapper.delete(id);
        return row;
    }

    @Override
    public List<ProfessionInspection> insertByCollection(List<ProfessionInspectionCreateDTO> createDTOList) {
        List<ProfessionInspection> professionInspectionList = new ArrayList<>();
        for (ProfessionInspectionCreateDTO createDTO : createDTOList) {
            ProfessionInspection professionInspection = ProfessionInspection.builder()
                    .name(createDTO.getName())
                    .createTime(LocalDateTime.now())
                    .createUserId(BaseContext.getCurrentId().getEmployeeId())
                    .build();
            professionInspectionList.add(professionInspection);
        }
        professionInspectionMapper.insertByCollection(professionInspectionList);
        return professionInspectionList;
    }
}
