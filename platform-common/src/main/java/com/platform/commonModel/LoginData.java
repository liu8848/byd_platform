package com.platform.commonModel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginData implements Serializable {
    @Schema(description = "主键id")
    private Long id;
    @Schema(description = "用户工号")
    private Long employeeId;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "姓名")
    private String name;
    @Schema(description = "jwt令牌")
    private String token;
}
