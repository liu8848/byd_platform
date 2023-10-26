package com.platform.config;

import com.platform.constant.RedisKeyConstant;
import com.platform.entity.Dictionary;
import com.platform.mapper.DictionaryMapper;
import com.platform.utils.RedisUtil;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DictInitConfiguration {
    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Autowired
    private RedisUtil redisUtil;

    @PostConstruct
    public void initDictionary()throws Throwable{
        log.info("---------------初始化数据字典-开始-----------------");

        load(RedisKeyConstant.FACTORY);
        load(RedisKeyConstant.BUSINESSDIVISION);
        load(RedisKeyConstant.DEPARTMENT);
        load(RedisKeyConstant.LOCATION);
        log.info("----------------数据字典加载-完成------------------");
    }

    private void load(String key) throws Throwable {
        log.info("-----------------loading   {}  -------------------",key);
        if(Boolean.TRUE.equals(redisUtil.hasKey(key))){
            redisUtil.del(key);
        }
        String methodName="get"+key.charAt(0)+key.toLowerCase().substring(1,key.length());
        Method method = dictionaryMapper.getClass().getMethod(methodName);
        List<Dictionary> factories= (List<Dictionary>) method.invoke(dictionaryMapper);
        Map<String, Dictionary> collect = factories.stream().collect(Collectors.toMap(f-> f.getDictValue().toString(), f -> f));
        redisUtil.hsetAll(key,collect);
    }
}
