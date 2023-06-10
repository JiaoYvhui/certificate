package com.manage.certificate.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manage.certificate.comm.exception.BizException;
import com.manage.certificate.entity.CertificateExam;
import com.manage.certificate.entity.CertificateStaff;
import com.manage.certificate.mapper.CertificateExamMapper;
import com.manage.certificate.mapper.CertificateStaffMapper;
import com.manage.certificate.service.CertificateStaffService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CertificateStaffServiceImpl implements CertificateStaffService {
    @Resource
    private CertificateStaffMapper mapper;

    @Resource
    private CertificateExamMapper examMapper;

    private final SqlSessionFactory sqlSessionFactory;

    public CertificateStaffServiceImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void save(CertificateStaff staff) {
        if (staff != null && staff.getId() != null) {
            mapper.update(staff);
        } else if (staff != null) {
            CertificateStaff byCard = mapper.findByCard(staff.getStaffCard(), 0);
            if (byCard != null) {
                throw new BizException("该身份证号已存在");
            }
            staff.preInsert();
            mapper.insert(staff);
        } else {
            throw new BizException("错误，实体类为空，请确认值是否传递正确");
        }
    }

    @Override
    public boolean batchInsert(List<CertificateStaff> staffs) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try {
            CertificateStaffMapper mappers = sqlSession.getMapper(CertificateStaffMapper.class);
            staffs.forEach(row -> {
                row.preInsert();
                mappers.insert(row);
            });
            //提交代码
            sqlSession.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            //发生异常回滚
            sqlSession.rollback();
            return false;
        } finally {
            //清除本地缓存
            sqlSession.clearCache();
        }
    }

    @Override
    public void delete(String id) {
        if (id != null && !"".equals(id)) {
            mapper.delete(id);
        } else {
            throw new BizException("错误，id为空，请确认值是否传递正确");
        }

    }

    @Override
    public PageInfo<CertificateStaff> findList(CertificateStaff staff) {
        if (staff.getPageNum() == null || staff.getPageNum() < 1) {
            staff.setPageNum(1);
        }
        if (staff.getPageSize() == null || staff.getPageSize() < 1) {
            staff.setPageSize(10);
        }
        PageHelper.startPage(staff.getPageNum(), staff.getPageSize());
        return new PageInfo<>(mapper.findList(staff));
    }

    public CertificateStaff findCertificate(CertificateStaff staff) {
        CertificateStaff certificate = mapper.findCertificate(staff);
        if (certificate != null) {
            List<CertificateExam> certificate1 = examMapper.findCertificate(certificate.getId());
            certificate.setExams(certificate1);
        } else {
            throw new BizException("未找到该学员");
        }
        return certificate;
    }

    public List<String> findStaffCard(List<String> staffCard) {
        return mapper.findStaffCard(staffCard);

    }
}
