package com.platform.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
@Slf4j
public class RedisConfiguration implements CachingConfigurer {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){

        log.info("------------------------加载Redis配置 -------------------");
        RedisTemplate<String,Object> template=new RedisTemplate<>();

        template.setConnectionFactory(factory);

        ObjectMapper om=new ObjectMapper();

        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL);
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer=new GenericJackson2JsonRedisSerializer(om);

        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setKeySerializer(new StringRedisSerializer());

        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }

    @Bean
    public HashOperations<String,String,Object>hashOperations(RedisTemplate<String,Object> redisTemplate){
        return redisTemplate.opsForHash();
    }

    @Bean
    public ValueOperations<String,Object> valueOperations(RedisTemplate<String,Object>redisTemplate){
        return redisTemplate.opsForValue();
    }

    @Bean
    public ListOperations<String,Object>listOperations(RedisTemplate<String,Object>redisTemplate){
        return redisTemplate.opsForList();
    }

    @Bean
    public SetOperations<String,Object> setOperations(RedisTemplate<String, Object>redisTemplate){
        return redisTemplate.opsForSet();
    }

    @Bean
    public ZSetOperations<String,Object> zSetOperations(RedisTemplate<String,Object>redisTemplate){
        return redisTemplate.opsForZSet();
    }
}
