package com.manage.certificate.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manage.certificate.comm.exception.BizException;
import com.manage.certificate.entity.CertificateExam;
import com.manage.certificate.entity.CertificateMeans;
import com.manage.certificate.mapper.CertificateExamMapper;
import com.manage.certificate.mapper.CertificateMeansMapper;
import com.manage.certificate.service.CertificateExamService;
import com.manage.certificate.service.CertificateMeansService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CertificateExamServiceImpl implements CertificateExamService {

    @Resource
    private CertificateExamMapper mapper;

    @Override
    public void save(CertificateExam exam) {
        if (exam != null && exam.getId() != null) {
            mapper.update(exam);
        } else if (exam != null) {
            exam.preInsert();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date startTime = sdf.parse(exam.getStartTime());
                Date endTime = sdf.parse(exam.getEndTime());
                if (startTime.compareTo(new Date()) <= 0) {
                    throw new BizException("考试开始时间小于当前时间");
                }
                if (endTime.compareTo(new Date()) <= 0) {
                    throw new BizException("考试结束时间小于当前时间");
                }
                if (endTime.compareTo(startTime) <= 0) {
                    throw new BizException("考试开始时间大于考试结束时间");
                }
                exam.setExamState("未开始");
            } catch (ParseException e) {
                e.printStackTrace();
                throw new BizException("考试时间类型传递错误，请主要时分秒是否传递正确");
            }
            exam.setCreateDate(new Date());
            mapper.insert(exam);
        } else {
            throw new BizException("错误，实体类为空，请确认值是否传递正确");
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
    public PageInfo<CertificateExam> findList(CertificateExam exam) {
        mapper.updateStateStart();
        mapper.updateStateEnd();
        if (exam.getPageNum() == null || exam.getPageNum() < 1) {
            exam.setPageNum(1);
        }
        if (exam.getPageSize() == null || exam.getPageSize() < 1) {
            exam.setPageSize(10);
        }
        PageHelper.startPage(exam.getPageNum(), exam.getPageSize());
        return new PageInfo<>(mapper.findList(exam));
    }
}
