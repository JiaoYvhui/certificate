package com.manage.certificate.controller;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.manage.certificate.comm.controller.BaseController;
import com.manage.certificate.comm.exception.BizException;
import com.manage.certificate.comm.exception.BizExceptionEnum;
import com.manage.certificate.comm.exception.BizResponseEnum;
import com.manage.certificate.comm.model.Response;
import com.manage.certificate.entity.CertificateStaff;
import com.manage.certificate.entity.CertificateUser;
import com.manage.certificate.service.CertificateStaffService;
import com.manage.certificate.service.CertificateUserService;
import com.manage.certificate.util.CertificateStaffListener;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;

@RestController
public class CertificateStaffController extends BaseController {

    @Resource
    private CertificateStaffService service;

    @Resource
    private CertificateUserService userService;

    @PostMapping("/a/staff/add")
    public Response addStaff(@Valid @RequestBody CertificateStaff staff) {
        if (staff != null) {
            staff.setId(null);
            service.save(staff);
            return super.successResponse(BizResponseEnum.SUCCEED_INSERT);
        }
        return super.failResponse(BizExceptionEnum.VALUE_ERROR);
    }

    @DeleteMapping("/a/staff/del")
    public Response deleteStaff(String id) {
        service.delete(id);
        return super.successResponse(BizResponseEnum.SUCCEED_DELETE);
    }

    @PutMapping("/a/staff/update")
    public Response updateStaff(@RequestBody CertificateStaff staff) {
        if (staff != null && staff.getId() != null) {
            service.save(staff);
            return super.successResponse(BizResponseEnum.SUCCEED_UPDATE);
        } else if (staff != null) {
            throw new BizException("错误，id为空，请确认值是否传递正确");
        }
        return super.failResponse(BizExceptionEnum.VALUE_ERROR);
    }

    @PostMapping("/a/staff")
    public Response findPage(@RequestBody CertificateStaff staff) {
        CertificateUser userId = userService.getUserId();
        if (!"管理员".equals(userId.getRole())) {
            staff.setStaffCompany(userId.getCompany());
        }
        PageInfo<CertificateStaff> list = service.findList(staff);
        return super.successResponse(BizResponseEnum.SUCCEED_QUERY, list);
    }

    @PostMapping("/a/staff/import")
    public Response importFile(@RequestParam("file") MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), CertificateStaff.class, new CertificateStaffListener(service)).sheet().doRead();
            if (CertificateStaffListener.SUCCESS != null) {
                return super.successResponse(BizResponseEnum.SUCCEED_IMPORT, CertificateStaffListener.SUCCESS);
            }
            return super.successResponse(BizResponseEnum.SUCCEED_IMPORT);
        } catch (IOException e) {
            throw new BizException("导入失败");
        }
    }

    @PostMapping("/a/staff/certificate")
    public Response findCertificate(@RequestBody CertificateStaff staff) {
        CertificateStaff certificate = service.findCertificate(staff);
        return super.successResponse(BizResponseEnum.SUCCEED_QUERY, certificate);
    }
}
