package com.platform.utils;

import com.platform.constant.RedisKeyConstant;
import com.platform.entity.Dictionary;
import com.platform.exception.BaseException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class DictUtil {
    @Autowired
    private RedisUtil redisUtil;
    public static Map<String, Map<String, Dictionary>> dictMap = new HashMap<>();

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
            HashMap<String, Dictionary> dictItem = (HashMap<String, Dictionary>) item.getValue();
            dictMap.put(innerKey, dictItem);
        }
    }

    public static String getDictName(String mapKey, String dictId) {
        Map<String, Dictionary> dictMap = DictUtil.dictMap.getOrDefault(mapKey, null);
        if (dictMap == null)
            throw new BaseException(mapKey + "字典不存在！");
        List<Dictionary> dictList = (List<Dictionary>) dictMap.getOrDefault(dictId, null);
        if (dictList == null)
            throw new BaseException(mapKey + ": {" + dictId + "}" + "不存在");
        Dictionary dictItem = dictList.get(0);
        return dictItem.getDictName();
    }
}
