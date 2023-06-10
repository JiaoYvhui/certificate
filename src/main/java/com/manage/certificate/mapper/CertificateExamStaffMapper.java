package com.manage.certificate.mapper;

import com.manage.certificate.entity.CertificateExam;
import com.manage.certificate.entity.CertificateExamStaff;
import com.manage.certificate.model.vo.CertificateExamStaffVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CertificateExamStaffMapper {
    CertificateExamStaff get(String id);

    void insert(CertificateExamStaff means);

    void update(CertificateExamStaff means);

    void delete(String id);

    List<CertificateExamStaff> findList(CertificateExamStaffVO examStaff);

    CertificateExamStaff getByStaffId(CertificateExamStaff examStaff);
}
