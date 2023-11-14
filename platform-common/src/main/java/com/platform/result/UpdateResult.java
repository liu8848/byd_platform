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

    @Schema(description = "原始内容")
    private Map<String, Object> oldMap;
    @Schema(description = "更新内容")
    private Map<String, Object> newMap;

    public static <T,U> UpdateResult<T> getUpdateContent(T newObject, U oldObject)  {
        Map<String, Object> oldO = new HashMap<>();
        Map<String, Object> newO = new HashMap<>();
        Field[] declaredFields = newObject.getClass().getDeclaredFields();
        try {
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                Object newVal = declaredField.get(newObject);
                Object oldVal = declaredField.get(oldObject);
                if (newVal != null && !newVal.equals(oldVal)) {
                    newO.put(declaredField.getName(), newVal);
                    oldO.put(declaredField.getName(), oldVal);
                }
            }
        }catch (IllegalAccessException ex){
            throw new RuntimeException(ex);
        }
        return new UpdateResult<>("修改成功", oldO, newO);
    }
}
