package com.manage.certificate.mapper;

import com.manage.certificate.entity.CertificateStaff;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CertificateStaffMapper {

    CertificateStaff get(String id);

    void insert(CertificateStaff staff);

    void delete(String id);

    void update(CertificateStaff staff);

    List<CertificateStaff> findList(CertificateStaff staff);

    CertificateStaff findByCard(String staffCard, Integer delFlag);

    CertificateStaff findCertificate(CertificateStaff staff);

    List<String> findStaffCard(List<String> staffCard);
}
