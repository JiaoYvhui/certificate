package com.manage.certificate.service;

import com.github.pagehelper.PageInfo;
import com.manage.certificate.entity.DictType;
import com.manage.certificate.entity.DictValue;

import java.util.List;

public interface DictTypeService {
    DictType get(String id);

    DictValue getDictValue(String id);

    List<DictType> findList(DictType dictType);

    PageInfo<DictType> findPage(DictType dictType);

    PageInfo<DictValue> findPage(DictValue dictValue);

    void saveDictValue(DictValue dictValue);

    void deleteDictValue(String id);

    void delete(String id);

    void save(DictType dictType);

    DictType getType(String id);
}
