package com.platform.mapper;

import com.github.pagehelper.Page;
import com.platform.dto.processes.ProcessPageQueryDTO;
import com.platform.entity.Process;
import com.platform.vo.ProcessApplicantVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProcessMapper {

    List<Process> getByApplicantId(Long applicantId);

    Page<ProcessApplicantVO> queryPageApplicant(ProcessPageQueryDTO processPageQueryDTO);

    String getMaxProcessId(String prefix);

    void insert(Process process);

    Process selectProcessById(String processId);

    void deleteByProcessId(String processId);

    void update(Process process);
}
