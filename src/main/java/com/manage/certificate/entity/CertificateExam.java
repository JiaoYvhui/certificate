package com.manage.certificate.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manage.certificate.model.dto.Page;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class CertificateExam extends Page {

    private Integer id;
    private String examState;
    @NotBlank(message = "考试名称不能为空")
    private String examName;
    @NotBlank(message = "考试等级不能为空")
    private String examLevel;
    @NotBlank(message = "操作形式不能为空")
    private String examForm;
    @NotBlank(message = "考试开始时间不能为空")
    private String startTime;
    @NotBlank(message = "考试结束时间不能为空")
    private String endTime;

    private String examAffiliated;

    private Date termValidity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExamState() {
        return examState;
    }

    public void setExamState(String examState) {
        this.examState = examState;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamLevel() {
        return examLevel;
    }

    public void setExamLevel(String examLevel) {
        this.examLevel = examLevel;
    }

    public String getExamForm() {
        return examForm;
    }

    public void setExamForm(String examForm) {
        this.examForm = examForm;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getExamAffiliated() {
        return examAffiliated;
    }

    public void setExamAffiliated(String examAffiliated) {
        this.examAffiliated = examAffiliated;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getTermValidity() {
        return termValidity;
    }

    public void setTermValidity(Date termValidity) {
        this.termValidity = termValidity;
    }
}
