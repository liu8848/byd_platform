package com.platform.service.dict;

import com.platform.entity.Dictionary;

import java.util.List;

public interface DictionaryService {

    List<Dictionary> getFactoryDict();

    List<Dictionary> getTechnologyDict();

    List<Dictionary> getNormalDict();
}
