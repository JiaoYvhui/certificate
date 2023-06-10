package com.manage.certificate.service;

import com.github.pagehelper.PageInfo;
import com.manage.certificate.comm.model.Response;
import com.manage.certificate.entity.CertificateUser;
import com.manage.certificate.model.dto.LoginDto;
import com.manage.certificate.model.vo.CertificateUserVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface CertificateUserService {
    List<CertificateUser> findAll();

    Map<String, Object > loginByPassword(LoginDto loginDto);

    String logout();

    @Transactional(readOnly = false)
    void save(CertificateUser user);

    @Transactional(readOnly = false)
    void delete(String phone);

    @Transactional(readOnly = false)
    void rebuildPassword(CertificateUserVO user);

    PageInfo<CertificateUser> findPage(CertificateUser certificateUser);

    @Transactional(readOnly = false)
    void updatePhone(CertificateUser user);

    CertificateUser getUserId();
}
