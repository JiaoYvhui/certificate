package com.manage.certificate.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manage.certificate.comm.exception.BizException;
import com.manage.certificate.entity.CertificateMeans;
import com.manage.certificate.mapper.CertificateMeansMapper;
import com.manage.certificate.service.CertificateMeansService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CertificateMeansServiceImpl implements CertificateMeansService {

    @Resource
    private CertificateMeansMapper mapper;

    @Override
    public void save(CertificateMeans means) {
        if (means != null && means.getId() != null) {
            mapper.update(means);
        } else if (means != null) {
            means.preInsert();
            means.setCreateDate(new Date());
            mapper.insert(means);
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
    public PageInfo<CertificateMeans> findList(CertificateMeans means) {
        if (means.getPageNum() == null || means.getPageNum() < 1) {
            means.setPageNum(1);
        }
        if (means.getPageSize() == null || means.getPageSize() < 1) {
            means.setPageSize(10);
        }
        PageHelper.startPage(means.getPageNum(), means.getPageSize());
        return new PageInfo<>(mapper.findList(means));
    }
}
