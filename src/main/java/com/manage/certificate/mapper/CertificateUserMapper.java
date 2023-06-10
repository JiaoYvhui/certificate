package com.manage.certificate.mapper;

import com.manage.certificate.entity.CertificateUser;
import com.manage.certificate.model.vo.CertificateUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CertificateUserMapper {
    CertificateUser getLoginPhone(String phone);

    List<CertificateUser> findAll();

    void insert(CertificateUser user);

    void update(CertificateUser user);

    void delete(String id);

    CertificateUser get(Integer id);

    List<CertificateUser> findList(CertificateUser user);

    void updatePassword(CertificateUserVO user);

    void updatePhone(CertificateUser user);

    CertificateUser findByPhone(String phone,Integer delFlag);

    void recovery(Integer id);

}
