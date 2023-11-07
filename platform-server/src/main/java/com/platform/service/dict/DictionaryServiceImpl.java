package com.platform.service.dict;

import com.platform.commonModel.Dictionary;
import com.platform.mapper.DictionaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Override
    public List<Dictionary> getFactoryDict() {
        return dictionaryMapper.getFactory();
    }

    @Override
    public List<Dictionary> getTechnologyDict() {
        return dictionaryMapper.getTechnologyDict();
    }

    @Override
    public List<Dictionary> getNormalDict() {
        return dictionaryMapper.getNormalDict();
    }
}
