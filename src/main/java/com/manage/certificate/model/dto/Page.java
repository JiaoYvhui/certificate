package com.manage.certificate.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Page {

    private Integer pageNum;

    private Integer pageSize;

    private Date createDate;

    /**
     * 新增时调用
     */
    public void preInsert(){
        this.createDate = new Date();
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
