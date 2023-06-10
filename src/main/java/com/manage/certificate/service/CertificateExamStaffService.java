package com.manage.certificate.service;

import com.github.pagehelper.PageInfo;
import com.manage.certificate.entity.CertificateExamStaff;
import com.manage.certificate.model.vo.CertificateExamStaffVO;
import org.springframework.transaction.annotation.Transactional;

public interface CertificateExamStaffService {
    @Transactional(readOnly = false)
    void save(CertificateExamStaff examStaff);

    @Transactional(readOnly = false)
    void delete(String id);

    PageInfo<CertificateExamStaff> findList(CertificateExamStaffVO examStaff);
}
