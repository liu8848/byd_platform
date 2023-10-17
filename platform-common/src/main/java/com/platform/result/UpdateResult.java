package com.platform.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "更新功能返回结果")
public class UpdateResult<T> implements Serializable {
    @Schema(description = "消息")
    private String msg;

    @Schema(description = "更新内容")
    private Map<String,String> updateMap;

    public static <T> UpdateResult<T> getUpdateContent(T newObject,T oldObject) throws IllegalAccessException {
        Map<String,String> updateResult=new HashMap<>();
        Field[] declaredFields = newObject.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            Object newVal = declaredField.get(newObject);
            Object oldVal = declaredField.get(oldObject);
            if(newVal!=null&&!newVal.equals(oldVal)){
                updateResult.put(declaredField.getName(),
                        (oldVal==null?"":oldVal.toString())+" --> "+newVal.toString());
            }
        }
        return new UpdateResult<>("修改成功",updateResult);
    }
}
