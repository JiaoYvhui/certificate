package com.manage.certificate.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.manage.certificate.model.dto.Page;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

public class CertificateStaff extends Page {
    private Integer id;
    @NotBlank(message = "公司信息不能为空")
    @ExcelProperty("公司信息")
    private String staffCompany;
    @NotBlank(message = "姓名不能为空")
    @ExcelProperty("姓名")
    private String staffName;
    @NotBlank(message = "手机号不能为空")
    @ExcelProperty("手机号")
    private String staffPhone;
    @NotBlank(message = "身份证号不能为空")
    @ExcelProperty("身份证号")
    private String staffCard;
    @ExcelProperty("民族")
    private String staffEthnic;
    @ExcelProperty("性别")
    private String staffGender;
    @ExcelProperty("出生日期")
    private Date staffBirthDate;
    private String staffAvatar;
    private Date staffObtainDate;

    private List<CertificateExam> exams;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStaffCompany() {
        return staffCompany;
    }

    public void setStaffCompany(String staffCompany) {
        this.staffCompany = staffCompany;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    public String getStaffCard() {
        return staffCard;
    }

    public void setStaffCard(String staffCard) {
        this.staffCard = staffCard;
    }

    public String getStaffEthnic() {
        return staffEthnic;
    }

    public void setStaffEthnic(String staffEthnic) {
        this.staffEthnic = staffEthnic;
    }

    public String getStaffGender() {
        return staffGender;
    }

    public void setStaffGender(String staffGender) {
        this.staffGender = staffGender;
    }

    public Date getStaffBirthDate() {
        return staffBirthDate;
    }

    public void setStaffBirthDate(Date staffBirthDate) {
        this.staffBirthDate = staffBirthDate;
    }

    public String getStaffAvatar() {
        return staffAvatar;
    }

    public void setStaffAvatar(String staffAvatar) {
        this.staffAvatar = staffAvatar;
    }

    public Date getStaffObtainDate() {
        return staffObtainDate;
    }

    public void setStaffObtainDate(Date staffObtainDate) {
        this.staffObtainDate = staffObtainDate;
    }

    public List<CertificateExam> getExams() {
        return exams;
    }

    public void setExams(List<CertificateExam> exams) {
        this.exams = exams;
    }
}
