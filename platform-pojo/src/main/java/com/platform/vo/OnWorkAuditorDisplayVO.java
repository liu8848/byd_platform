package com.platform.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "在职审核员台账展示模型")
public class OnWorkAuditorDisplayVO implements Serializable {
    private Map<String,Integer> auditorSumMap;
    private String buName;
    private String recordFactoryName;
    private List<AuditorDisplayVO> auditorList;
}
