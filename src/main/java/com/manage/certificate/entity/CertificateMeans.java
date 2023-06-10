package com.manage.certificate.entity;

import com.manage.certificate.model.dto.Page;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class CertificateMeans extends Page {
    private Integer id;
    @NotBlank(message = "资料名称不能为空")
    private String meansName;
    @NotBlank(message = "证书等级不能为空")
    private String certificateLevel;
    @NotBlank(message = "学习资料不能为空")
    private String studyMeans;
    @NotBlank(message = "考试资料不能为空")
    private String examMeans;
    private String meansAffiliated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeansName() {
        return meansName;
    }

    public void setMeansName(String meansName) {
        this.meansName = meansName;
    }

    public String getStudyMeans() {
        return studyMeans;
    }

    public void setStudyMeans(String studyMeans) {
        this.studyMeans = studyMeans;
    }

    public String getExamMeans() {
        return examMeans;
    }

    public void setExamMeans(String examMeans) {
        this.examMeans = examMeans;
    }

    public String getCertificateLevel() {
        return certificateLevel;
    }

    public void setCertificateLevel(String certificateLevel) {
        this.certificateLevel = certificateLevel;
    }

    public String getMeansAffiliated() {
        return meansAffiliated;
    }

    public void setMeansAffiliated(String meansAffiliated) {
        this.meansAffiliated = meansAffiliated;
    }
}
