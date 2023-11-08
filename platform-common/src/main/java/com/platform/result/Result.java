package com.platform.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Schema(description = "相应体数据结构")
public class Result<T> implements Serializable {
    @Schema(description = "响应状态码")
    private Integer code;
    @Schema(description = "响应信息")
    private String msg;
    @Schema(description = "返回的数据信息模型")
    private T data;

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.code = 1;
        return result;
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<>();
        result.data = object;
        result.code = 1;
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }

    public static <T> Result<T> errorList(List<String> errMsgList){
        Result result=new Result();
        result.code=0;
        result.data=errMsgList;
        return result;
    }
}
