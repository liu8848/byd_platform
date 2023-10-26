package com.platform.utils;

import com.platform.constant.RedisKeyConstant;
import com.platform.entity.Dictionary;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class DictUtil {
    @Autowired
    private RedisUtil redisUtil;
    public static Map<String,Map<String,String>> dictMap=new HashMap<>();

    @PostConstruct
    public void dictInit(){
        load(RedisKeyConstant.FACTORY);
        load(RedisKeyConstant.DEPARTMENT);
        load(RedisKeyConstant.BUSINESSDIVISION);
        load(RedisKeyConstant.LOCATION);
    }

    private void load(String key){
        Map<Object, Object> hmget = redisUtil.hmget(key);
        Map<String,String> map=new HashMap<>();
        for (var item:hmget.entrySet()) {
            String innerKey = item.getKey().toString();
            String dictName = ((Dictionary) item.getValue()).getDictName();
            map.put(innerKey,dictName);
        }
        dictMap.put(key,map);
    }
}
