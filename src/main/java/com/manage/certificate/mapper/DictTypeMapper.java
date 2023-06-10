package com.manage.certificate.mapper;

import com.manage.certificate.entity.DictType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictTypeMapper {

    DictType get(String id);

    List<DictType> findList(DictType dictType);

    List<DictType> findAllList();

    void insert(DictType dictType);

    void update(DictType dictType);

    void delete(String id);

    void deleteByLogic(String id);

    DictType getType(String type);
}
