package com.manage.certificate.controller;

import com.github.pagehelper.PageInfo;
import com.manage.certificate.comm.controller.BaseController;
import com.manage.certificate.comm.exception.BizException;
import com.manage.certificate.comm.exception.BizExceptionEnum;
import com.manage.certificate.comm.exception.BizResponseEnum;
import com.manage.certificate.comm.model.Response;
import com.manage.certificate.entity.CertificateMeans;
import com.manage.certificate.entity.CertificateUser;
import com.manage.certificate.service.CertificateMeansService;
import com.manage.certificate.service.CertificateUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class CertificateMeansController extends BaseController {

    @Resource
    private CertificateMeansService service;

    @Resource
    private CertificateUserService userService;

    @PostMapping("/a/means/add")
    public Response addMeans(@Valid @RequestBody CertificateMeans means){
        if (means!=null) {
            CertificateUser userId = userService.getUserId();
            means.setId(null);
            means.setMeansAffiliated(userId.getCompany());
            service.save(means);
            return super.successResponse(BizResponseEnum.SUCCEED_INSERT);
        }
        return super.failResponse(BizExceptionEnum.VALUE_ERROR);
    }

    @DeleteMapping("/a/means/del")
    public Response deleteMeans(String id){
        service.delete(id);
        return super.successResponse(BizResponseEnum.SUCCEED_DELETE);
    }

    @PutMapping("/a/means/update")
    public Response updateMeans(@Valid @RequestBody CertificateMeans means){
        if (means!=null && means.getId()!=null) {
            service.save(means);
            return super.successResponse(BizResponseEnum.SUCCEED_UPDATE);
        } else if (means!=null) {
            throw new BizException("错误，id为空，请确认值是否传递正确");
        }
        return super.failResponse(BizExceptionEnum.VALUE_ERROR);
    }

    @PostMapping("/a/means")
    public Response findPage(@RequestBody CertificateMeans means){
        CertificateUser userId = userService.getUserId();
        if (!"管理员".equals(userId.getRole())) {
            means.setMeansAffiliated(userId.getCompany());
        }
        PageInfo<CertificateMeans> list = service.findList(means);
        return super.successResponse(BizResponseEnum.SUCCEED_QUERY,list);
    }
}
