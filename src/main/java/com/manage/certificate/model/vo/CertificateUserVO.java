package com.manage.certificate.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificateUserVO {
    @NotNull(message = "ID不能为空")
    private Integer id;
    @NotBlank(message = "密码不能为空")
    private String password;

}
