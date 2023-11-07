package com.platform.mapper;

import com.platform.commonModel.Dictionary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictionaryMapper {

    List<Dictionary> getFactory();

    List<Dictionary> getBusinessdivision();

    List<Dictionary> getDepartment();

    List<Dictionary> getLocation();

    List<Dictionary> getProfessionInspection();

    List<Dictionary> getTechnologyDict();

    List<Dictionary> getNormalDict();
}
