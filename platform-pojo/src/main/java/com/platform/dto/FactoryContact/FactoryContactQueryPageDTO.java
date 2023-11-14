package com.platform.dto.FactoryContact;

import com.platform.dto.BasePageQueryDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "工厂体系接口人分页查询数据模型")
public class FactoryContactQueryPageDTO extends BasePageQueryDTO implements Serializable {

    @Schema(description = "事业部编号")
    private Long buId;

    @Schema(description = "备案工厂编号")
    private Long recordFactoryId;

}
