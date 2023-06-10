package com.manage.certificate.mapper;

import com.manage.certificate.entity.CertificateExam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CertificateExamMapper {
    CertificateExam get(String id);

    void insert(CertificateExam means);

    void update(CertificateExam means);

    void delete(String id);

    List<CertificateExam> findList(CertificateExam means);

    void updateStateStart();

    void updateStateEnd();

    List<CertificateExam> findCertificate(Integer id);
}
