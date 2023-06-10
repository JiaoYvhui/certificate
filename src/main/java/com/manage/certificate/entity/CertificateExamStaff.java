package com.manage.certificate.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manage.certificate.model.dto.Page;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class CertificateExamStaff extends Page {
    private Integer id;
    @NotNull(message = "学员ID不能为空")
    private String staffId;
    @NotNull(message = "考试ID不能为空")
    private Integer examId;
    private String isApprove;

    private Date obtainDate;

    private String writtenExamImg;
    private String sampleImg;

    private Date termValidity;

    private CertificateStaff staff;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(String isApprove) {
        this.isApprove = isApprove;
    }

    public CertificateStaff getStaff() {
        return staff;
    }

    public void setStaff(CertificateStaff staff) {
        this.staff = staff;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getObtainDate() {
        return obtainDate;
    }

    public void setObtainDate(Date obtainDate) {
        this.obtainDate = obtainDate;
    }

    public String getWrittenExamImg() {
        return writtenExamImg;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getTermValidity() {
        return termValidity;
    }

    public void setTermValidity(Date termValidity) {
        this.termValidity = termValidity;
    }

    public void setWrittenExamImg(String writtenExamImg) {
        this.writtenExamImg = writtenExamImg;
    }

    public String getSampleImg() {
        return sampleImg;
    }

    public void setSampleImg(String sampleImg) {
        this.sampleImg = sampleImg;
    }
}
