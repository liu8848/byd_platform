package com.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@Schema(description = "上传文件数据模型")
@TableName(value = "upload_file")
public class UploadFile implements Serializable {

    @Schema(description = "主键")
    @TableId
    private Long id;

    @Schema(description = "文件唯一标识")
    @TableField(value = "uuid")
    private String uuid;

    @Schema(description = "文件存储路径")
    @TableField(value = "file_path")
    private String filePath;
}
