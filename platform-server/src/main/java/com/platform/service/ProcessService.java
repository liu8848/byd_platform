package com.platform.service;

import com.platform.dto.processes.ProcessCreateDTO;
import com.platform.dto.processes.ProcessPageQueryDTO;
import com.platform.dto.processes.ProcessUpdateDTO;
import com.platform.entity.Process;
import com.platform.result.PageResult;
import com.platform.vo.ProcessApplicantVO;

import java.util.List;

public interface ProcessService {

    List<Process> getByApplicantId(Long applicantId);

    PageResult<ProcessApplicantVO> pageQueryByApplicant(ProcessPageQueryDTO processPageQueryDTO);

    void createProcess(ProcessCreateDTO processCreateDTO);

    void deleteProcessById(String processId);

    void updateProcessById(ProcessUpdateDTO processUpdateDTO);
}
