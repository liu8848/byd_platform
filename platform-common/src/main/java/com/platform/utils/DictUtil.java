package com.platform.utils;

import com.platform.constant.RedisKeyConstant;
import com.platform.commonModel.Dictionary;
import com.platform.exception.BaseException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class DictUtil {
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 字典值————》字典名称
     */
    public static Map<String, Map<Long, Dictionary>> dictMap = new HashMap<>();

    /**
     * 字典名称——》字典值
     */
    public static Map<String,Map<String,Dictionary>> dictMapNameToValue=new HashMap<>();

    @PostConstruct
    public void dictInit() {
        load(RedisKeyConstant.FACTORY);
        load(RedisKeyConstant.DEPARTMENT);
        load(RedisKeyConstant.BUSINESSDIVISION);
        load(RedisKeyConstant.LOCATION);
        load(RedisKeyConstant.PROFESSION_INSPECTION);
        load(RedisKeyConstant.NORMAL_DICT);
    }

    private void load(String key) {
        Map<Object, Object> hmget = redisUtil.hmget(key);
        for (var item : hmget.entrySet()) {
            String innerKey = item.getKey().toString();
            HashMap<Long, Dictionary> dictItem = (HashMap<Long, Dictionary>) item.getValue();

            HashMap<String,Dictionary> dictItem2=new HashMap<>();
            for (Dictionary dict:dictItem.values()) {
                dictItem2.put(dict.getDictName(),dict);
            }
            dictMap.put(innerKey, dictItem);
            dictMapNameToValue.put(innerKey,dictItem2);
        }
    }

    public static String getDictName(String mapKey, Long dictId) {
        Map<Long, Dictionary> itemMap = DictUtil.dictMap.getOrDefault(mapKey, null);
        if (itemMap == null)
            throw new BaseException(mapKey + "字典不存在！");
        Dictionary dictItem = itemMap.getOrDefault(dictId.toString(), null);
        if (dictItem == null)
            throw new BaseException(mapKey + ": {" + dictId + "}" + "不存在");
        return dictItem.getDictName();
    }

    public static Long getDictValue(String mapKey, String dictName){
        Map<String,Dictionary> itemMap=dictMapNameToValue.getOrDefault(mapKey,null);
        if (itemMap == null)
            throw new BaseException(mapKey + "字典不存在！");
        Dictionary dictItem = (Dictionary) itemMap.getOrDefault(dictName, null);
        if (dictItem == null)
            throw new BaseException(mapKey + ": {" + dictName + "}" + "不存在");
        return dictItem.getDictValue();
    }
}
