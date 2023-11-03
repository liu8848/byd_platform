package com.platform.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.platform.enums.LevelMatch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuditorStandingBookInWorkVO implements Serializable {
    private Long buId;
    private String buName;
    private Long recordFactoryId;
    private String recordFactoryName;
    private String levelMatch;
    private String level;
    private long sa;
    private long a;
    private long pa;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime warnTime;


    private List<AuditorDisplayVO> auditors;
}
