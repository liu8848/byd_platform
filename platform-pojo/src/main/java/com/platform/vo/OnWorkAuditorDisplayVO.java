package com.platform.vo;

import com.platform.entity.Auditor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "在职审核员台账展示模型")
public class OnWorkAuditorDisplayVO implements Serializable {
    private boolean isMatch;
    private LocalDateTime warnTime;
    private int level;
    private String buName;
    private String recordFactoryName;
    private List<AuditorDisplayVO> auditorVOList;
    private Map<String,Long> auditorLevelCnt;


}
