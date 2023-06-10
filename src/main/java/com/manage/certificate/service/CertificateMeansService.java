package com.manage.certificate.service;

import com.github.pagehelper.PageInfo;
import com.manage.certificate.entity.CertificateMeans;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CertificateMeansService {
    @Transactional(readOnly = false)
    void save(CertificateMeans means);
    @Transactional(readOnly = false)
    void delete(String id);

    PageInfo<CertificateMeans> findList(CertificateMeans means);
}
