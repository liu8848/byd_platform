package com.platform.mapper;

import com.platform.entity.Dictionary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictionaryMapper {
    List<com.platform.entity.Dictionary> getDictMap();

    List<Dictionary> getFactory();

    List<Dictionary> getBusinessdivision();
    List<Dictionary> getDepartment();
    List<Dictionary> getLocation();
}
