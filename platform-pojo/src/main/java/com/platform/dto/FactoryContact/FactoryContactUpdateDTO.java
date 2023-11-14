package com.platform.dto.FactoryContact;

import com.platform.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "工厂体系接口人修改数据模型")
public class FactoryContactUpdateDTO extends BaseEntity implements Serializable {
    @Schema(description = "工号")
    private Long employeeId=null;

    @Schema(description = "事业部编号")
    private Long buId=null;

    @Schema(description = "备案工厂编号")
    private Long recordFactoryId=null;

    @Schema(description = "电话")
    private String phone;

    @Schema(description = "邮箱")
    private String email;
}
