package com.manage.certificate.service;

import com.github.pagehelper.PageInfo;
import com.manage.certificate.entity.CertificateExam;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false)
public interface CertificateExamService {

    @Transactional(readOnly = false)
    void save(CertificateExam exam);

    @Transactional(readOnly = false)
    void delete(String id);

    PageInfo<CertificateExam> findList(CertificateExam exam);
}
