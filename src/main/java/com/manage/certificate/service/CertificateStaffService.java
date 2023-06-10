package com.manage.certificate.service;

import com.github.pagehelper.PageInfo;
import com.manage.certificate.entity.CertificateStaff;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CertificateStaffService {

    @Transactional(readOnly = false)
    void save(CertificateStaff staff);

    @Transactional(readOnly = false)
    boolean batchInsert(List<CertificateStaff> staffs);

    @Transactional(readOnly = false)
    void delete(String id);

    PageInfo<CertificateStaff> findList(CertificateStaff staff);

    CertificateStaff findCertificate(CertificateStaff staff);

    List<String> findStaffCard(List<String> staffCard);
}
