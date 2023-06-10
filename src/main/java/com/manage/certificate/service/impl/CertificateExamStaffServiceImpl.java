package com.manage.certificate.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manage.certificate.comm.exception.BizException;
import com.manage.certificate.entity.CertificateExamStaff;
import com.manage.certificate.entity.CertificateStaff;
import com.manage.certificate.mapper.CertificateExamStaffMapper;
import com.manage.certificate.mapper.CertificateStaffMapper;
import com.manage.certificate.model.vo.CertificateExamStaffVO;
import com.manage.certificate.service.CertificateExamStaffService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CertificateExamStaffServiceImpl implements CertificateExamStaffService {

    @Resource
    private CertificateExamStaffMapper mapper;

    @Resource
    private CertificateStaffMapper staffMapper;

    private final SqlSessionFactory sqlSessionFactory;

    public CertificateExamStaffServiceImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void save(CertificateExamStaff examStaff) {
        if (examStaff != null && examStaff.getId() != null) {
            examStaff.setObtainDate(new Date());
            mapper.update(examStaff);
        } else if (examStaff != null) {
            boolean contains = examStaff.getStaffId().contains(",");
            if (!contains) {
                CertificateExamStaff byStaffId = mapper.getByStaffId(examStaff);
                if (byStaffId != null) {
                    throw new BizException("该考试中已有该学员");
                }
                examStaff.preInsert();
                examStaff.setCreateDate(new Date());
                mapper.insert(examStaff);
            } else {
                List<CertificateExamStaff> certificateExamStaffs = new ArrayList<>();
                String[] split = examStaff.getStaffId().split(",");
                for (String s : split) {
                    CertificateExamStaff certificateExamStaff = new CertificateExamStaff();
                    certificateExamStaff.setStaffId(s);
                    certificateExamStaff.setExamId(examStaff.getExamId());
                    CertificateExamStaff byStaffId = mapper.getByStaffId(certificateExamStaff);
                    if (byStaffId != null) {
                        CertificateStaff certificateStaff = staffMapper.get(s);
                        throw new BizException("该考试中已有" + certificateStaff.getStaffName() + "学员，请重新选择");
                    }
                    certificateExamStaffs.add(certificateExamStaff);
                }
                batchInsert(certificateExamStaffs);
            }
        } else {
            throw new BizException("错误，实体类为空，请确认值是否传递正确");
        }
    }

    @Override
    public void delete(String id) {
        if (id != null) {
            mapper.delete(id);
        } else {
            throw new BizException("错误，id为空，请确认值是否传递正确");
        }
    }

    @Override
    public PageInfo<CertificateExamStaff> findList(CertificateExamStaffVO examStaff) {
        if (examStaff.getExamId() != null && !"".equals(examStaff.getExamId())) {
            if (examStaff.getPageNum() == null || examStaff.getPageNum() < 1) {
                examStaff.setPageNum(1);
            }
            if (examStaff.getPageSize() == null || examStaff.getPageSize() < 1) {
                examStaff.setPageSize(10);
            }
            PageHelper.startPage(examStaff.getPageNum(), examStaff.getPageSize());
            return new PageInfo<>(mapper.findList(examStaff));
        } else {
            throw new BizException("考试ID为空");
        }
    }

    public boolean batchInsert(List<CertificateExamStaff> staffs) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try {
            CertificateExamStaffMapper mappers = sqlSession.getMapper(CertificateExamStaffMapper.class);
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
}
