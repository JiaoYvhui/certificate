package com.manage.certificate.model.vo;

import com.manage.certificate.model.dto.Page;

public class CertificateExamStaffVO extends Page {
    private String examId;

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }
}
