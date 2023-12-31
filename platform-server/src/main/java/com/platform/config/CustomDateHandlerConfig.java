package com.platform.config;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.platform.properties.DateFormatProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

@Configuration
@Slf4j
public class CustomDateHandlerConfig {

    @Autowired
    private DateFormatProperties dateFormatProperties;

    /**
     * DATE转换器
     */
    @Bean
    public Converter<String, Date> dateConverter(){
        log.info("------------------加载Date转换器------------------------------");
        return new Converter<String,Date>() {
            @Override
            public Date convert(String source) {
                return DateUtil.parse(source.trim());
            }
        };
    }

    /**
     * LocalDate转换器
     */
    @Bean
    public Converter<String, LocalDate> localDateConverter(){
        log.info("------------------加载LocalDate转换器------------------------------");
        return new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(String source) {
                return LocalDate.parse(source, DateTimeFormatter.ofPattern(dateFormatProperties.getDEFAULT_DATE_FORMAT()));
            }
        };
    }

    /**
     * LocalTime转换器
     */
    @Bean
    public Converter<String, LocalTime> localTimeConverter(){
        log.info("------------------加载LocalTime转换器------------------------------");
        return new Converter<String, LocalTime>() {
            @Override
            public LocalTime convert(String source) {
                return LocalTime.parse(source,DateTimeFormatter.ofPattern(dateFormatProperties.getDEFAULT_TIME_FORMAT()));
            }
        };
    }

    /**
     * LocalDateTime转换器
     */
    @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter(){
        log.info("------------------加载LocalDateTime转换器------------------------------");
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {
                log.info("日期格式转换");
                return LocalDateTime.parse(source,DateTimeFormatter.ofPattern(dateFormatProperties.getDEFAULT_DATE_TIME_FORMAT()));
            }
        };
    }

    /**
     * Json序列化和反序列化转换器
     */
    @Bean
    public ObjectMapper objectMapper(){

        log.info("------------------加载转换器---------------------");
        ObjectMapper objectMapper=new ObjectMapper();

        //java8日期 Local系列序列化与反序列化模块
        JavaTimeModule javaTimeModule=new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class,new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateFormatProperties.getDEFAULT_DATE_TIME_FORMAT())));
        javaTimeModule.addSerializer(LocalDate.class,new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormatProperties.getDEFAULT_DATE_FORMAT())));
        javaTimeModule.addSerializer(LocalTime.class,new LocalTimeSerializer(DateTimeFormatter.ofPattern(dateFormatProperties.getDEFAULT_TIME_FORMAT())));
        javaTimeModule.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateFormatProperties.getDEFAULT_DATE_TIME_FORMAT())));
        javaTimeModule.addDeserializer(LocalDate.class,new LocalDateDeserializer(DateTimeFormatter.ofPattern(dateFormatProperties.getDEFAULT_DATE_FORMAT())));
        javaTimeModule.addDeserializer(LocalTime.class,new LocalTimeDeserializer(DateTimeFormatter.ofPattern(dateFormatProperties.getDEFAULT_TIME_FORMAT())));

        objectMapper.registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(javaTimeModule);

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        //忽略json字符串中不识别的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        //忽略无法转换的对象
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        //格式化输出
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT,true);
        //NULL不参与转换
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //制定时区
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        objectMapper.setDateFormat(new SimpleDateFormat(dateFormatProperties.getDEFAULT_DATE_TIME_FORMAT()));

        return objectMapper;
    }

}
