package com.platform.config;

import com.platform.constant.RedisKeyConstant;
import com.platform.commonModel.Dictionary;
import com.platform.mapper.DictionaryMapper;
import com.platform.utils.RedisUtil;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
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
    public void initDictionary() throws Throwable {
        log.info("---------------初始化数据字典-开始-----------------");
        load(RedisKeyConstant.NORMAL_DICT);
        load(RedisKeyConstant.FACTORY);
        load(RedisKeyConstant.BUSINESSDIVISION);
        load(RedisKeyConstant.DEPARTMENT);
        load(RedisKeyConstant.LOCATION);
        load(RedisKeyConstant.PROFESSION_INSPECTION);
        log.info("----------------数据字典加载-完成------------------");
    }

    private void load(String key) throws Throwable {
        log.info("-----------------loading   {}  -------------------", key);
        if (Boolean.TRUE.equals(redisUtil.hasKey(key))) {
            redisUtil.del(key);
        }
        StringBuilder methodName = new StringBuilder();
        if (key.contains("_")) {
            String[] strs = key.split("_");
            methodName.append("get");
            for (var str : strs) {
                methodName.append(str.charAt(0)).append(str.toLowerCase(), 1, str.length());
            }
        } else
            methodName = new StringBuilder("get" + key.charAt(0) + key.toLowerCase().substring(1, key.length()));
        Method method = dictionaryMapper.getClass().getMethod(methodName.toString());
        List<Dictionary> dictList = (List<Dictionary>) method.invoke(dictionaryMapper);
        Map<String, Map<Long, List<Dictionary>>> collect = dictList.stream().collect(Collectors.groupingBy(Dictionary::getDictId, Collectors.groupingBy(Dictionary::getDictValue)));
        Map<String,Map<Long,Dictionary>> result=new HashMap<>();
        for (var entry:collect.entrySet()) {
            Map<Long,Dictionary> temp=new HashMap<>();
            for (var t:entry.getValue().entrySet()) {
                temp.put(t.getKey(),t.getValue().get(0));
            }
            result.put(entry.getKey(), temp);
        }
        redisUtil.hsetAll(key,result);

    }
}
