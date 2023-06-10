package com.manage.certificate.entity;

import com.manage.certificate.model.dto.Page;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.UUID;

public class DictType extends Page {
    private String id;
    private String type;
    private String description;
    private String value;
    private String sort;
    private List<DictValue> dictValueList = Lists.newArrayList();        // 子表列表

    public void uuid() {
        this.id = UUID.randomUUID().toString().replaceAll("-", "");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public List<DictValue> getDictValueList() {
        return dictValueList;
    }

    public void setDictValueList(List<DictValue> dictValueList) {
        this.dictValueList = dictValueList;
    }
}
