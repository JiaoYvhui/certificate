package com.manage.certificate.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manage.certificate.model.dto.Page;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CertificateUser extends Page {
    private Integer id;
    @NotNull(message = "账号不能为空")
    private String phone;
    @NotNull(message = "密码不能为空")
    private String password;
    @NotNull(message = "公司名称不能为空")
    private String enterpriseName;
    @NotNull(message = "邮箱不能为空")
    private String email;
    @NotBlank(message = "角色不能为空")
    private String role;
    @NotBlank(message = "所属公司不能为空")
    private String company;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "CertificateUser{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
