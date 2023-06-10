package com.manage.certificate.entity;

import com.manage.certificate.model.dto.Page;

import java.util.UUID;

public class DictValue extends Page {
    private String id;
    private DictType dictType;
    private String label;
    private String value;
    private String sort;

    public void uuid() {
        this.id = UUID.randomUUID().toString().replaceAll("-", "");
    }
    public DictValue(DictType dictType) {
        this.dictType = dictType;
    }

    public DictValue(String id) {
        this.id = id;
    }

    public DictValue() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DictType getDictType() {
        return dictType;
    }

    public void setDictType(DictType dictType) {
        this.dictType = dictType;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
}
