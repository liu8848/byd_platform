package com.platform.dto.auditors;

import com.platform.dto.BasePageQueryDTO;
import com.platform.enums.AuditorLevel;
import com.platform.enums.LevelMatch;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
public class AuditorPageQueryDTO extends BasePageQueryDTO {
    private Integer levelMatch;
    private Integer level;
    private Long buId;
    private Long recordFactoryId;
    private String employeeName;
    private Integer auditorLevel;
}
