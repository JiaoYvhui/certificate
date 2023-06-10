package com.manage.certificate.controller;

import com.github.pagehelper.PageInfo;
import com.manage.certificate.comm.controller.BaseController;
import com.manage.certificate.comm.exception.BizException;
import com.manage.certificate.comm.exception.BizExceptionEnum;
import com.manage.certificate.comm.exception.BizResponseEnum;
import com.manage.certificate.comm.model.Response;
import com.manage.certificate.entity.CertificateExam;
import com.manage.certificate.entity.CertificateUser;
import com.manage.certificate.service.CertificateExamService;
import com.manage.certificate.service.CertificateUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class CertificateExamController extends BaseController {

    @Resource
    private CertificateExamService service;

    @Resource
    private CertificateUserService userService;

    @PostMapping("/a/exam/add")
    public Response addExam(@Valid @RequestBody CertificateExam exam) {
        if (exam != null) {
            exam.setId(null);
            CertificateUser userId = userService.getUserId();
            exam.setExamAffiliated(userId.getCompany());
            service.save(exam);
            return super.successResponse(BizResponseEnum.SUCCEED_INSERT);
        }
        return super.failResponse(BizExceptionEnum.VALUE_ERROR);
    }

    @DeleteMapping("/a/exam/del")
    public Response deleteExam(String id) {
        service.delete(id);
        return super.successResponse(BizResponseEnum.SUCCEED_DELETE);
    }

    @PutMapping("/a/exam/update")
    public Response updateExam(@RequestBody CertificateExam exam) {
        if (exam != null && exam.getId() != null) {
            service.save(exam);
            return super.successResponse(BizResponseEnum.SUCCEED_UPDATE);
        } else if (exam != null) {
            throw new BizException("错误，id为空，请确认值是否传递正确");
        }
        return super.failResponse(BizExceptionEnum.VALUE_ERROR);
    }

    @PostMapping("/a/exam")
    public Response findPage(@RequestBody CertificateExam exam) {
        CertificateUser userId = userService.getUserId();
        if (!"管理员".equals(userId.getRole())) {
            exam.setExamAffiliated(userId.getCompany());
        }
        PageInfo<CertificateExam> list = service.findList(exam);
        return super.successResponse(BizResponseEnum.SUCCEED_QUERY, list);
    }
}
