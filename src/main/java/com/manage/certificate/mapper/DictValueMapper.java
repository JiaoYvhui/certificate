package com.manage.certificate.mapper;

import com.manage.certificate.entity.DictValue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictValueMapper {

    DictValue get(String id);

    List<DictValue> findList(DictValue dictValue);

    List<DictValue> findAllList(DictValue dictValue);

    void insert(DictValue dictValue);

    void update(DictValue dictValue);

    void delete(DictValue dictValue);

    void deleteByLogic(DictValue dictValue);

}
