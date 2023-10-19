package com.platform.service;

import com.platform.vo.AuditorDisplayVO;
import com.platform.vo.OnWorkAuditorDisplayVO;

import java.util.List;

public interface AuditorService {
    List<OnWorkAuditorDisplayVO> getOnWorkAuditor();
}
