package com.manage.certificate.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manage.certificate.comm.exception.BizException;
import com.manage.certificate.entity.DictType;
import com.manage.certificate.entity.DictValue;
import com.manage.certificate.mapper.DictTypeMapper;
import com.manage.certificate.mapper.DictValueMapper;
import com.manage.certificate.service.DictTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DictTypeServiceImpl implements DictTypeService {
    @Resource
    private DictValueMapper dictValueMapper;

    @Resource
    private DictTypeMapper dictTypeMapper;

    public DictType get(String id) {
        DictType dictType = dictTypeMapper.get(id);
        dictType.setDictValueList(dictValueMapper.findList(new DictValue(dictType)));
        return dictType;
    }

    public DictType getType(String type){
        DictType dictType = dictTypeMapper.getType(type);
        if (dictType!=null) {
            dictType.setDictValueList(dictValueMapper.findList(new DictValue(dictType)));
        }
        return dictType;
    }

    public DictValue getDictValue(String id) {
        return dictValueMapper.get(id);
    }

    public List<DictType> findList(DictType dictType) {
        return dictTypeMapper.findList(dictType);
    }

    public PageInfo<DictType> findPage(DictType dictType) {
        if (dictType.getPageNum() == null || dictType.getPageNum() < 1) {
            dictType.setPageNum(1);
        }
        if (dictType.getPageSize() == null || dictType.getPageSize() < 1) {
            dictType.setPageSize(10);
        }
        PageHelper.startPage(dictType.getPageNum(), dictType.getPageSize());
        return new PageInfo<>(dictTypeMapper.findList(dictType));
    }

    @Transactional(readOnly = false)
    public void saveDictValue(DictValue dictValue) {
        if (StringUtils.isBlank(dictValue.getId())) {
            dictValue.preInsert();
            dictValue.uuid();
            dictValueMapper.insert(dictValue);
        } else {
            dictValueMapper.update(dictValue);
        }
    }

    @Transactional(readOnly = false)
    public void deleteDictValue(String id) {
        dictValueMapper.delete(new DictValue(id));
    }

    public PageInfo<DictValue> findPage(DictValue dictValue) {
        if (dictValue.getPageNum() == null || dictValue.getPageNum() < 1) {
            dictValue.setPageNum(1);
        }
        if (dictValue.getPageSize() == null || dictValue.getPageSize() < 1) {
            dictValue.setPageSize(10);
        }
        PageHelper.startPage(dictValue.getPageNum(), dictValue.getPageSize());
        return new PageInfo<>(dictValueMapper.findList(dictValue));
    }

    @Transactional(readOnly = false)
    public void delete(String id) {
        if (id != null && !"".equals(id)) {
            dictTypeMapper.delete(id);
            DictType dictType = dictTypeMapper.get(id);
            if (dictType != null) {
                dictValueMapper.delete(new DictValue(dictType));
            }
        } else {
            throw new BizException("错误，id为空，请确认值是否传递正确");
        }
    }

    @Transactional(readOnly = false)
    public void save(DictType dictType) {
        if (StringUtils.isBlank(dictType.getId())) {
            dictType.preInsert();
            dictType.uuid();
            dictTypeMapper.insert(dictType);
        } else {
            dictTypeMapper.update(dictType);
        }
    }
}
