package com.manage.certificate.mapper;

import com.manage.certificate.entity.CertificateMeans;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CertificateMeansMapper {
    CertificateMeans get(String id);

    void insert(CertificateMeans means);

    void update(CertificateMeans means);

    void delete(String id);

    List<CertificateMeans> findList(CertificateMeans means);
}
