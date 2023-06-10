package com.manage.certificate.controller;

import com.github.pagehelper.PageInfo;
import com.manage.certificate.comm.controller.BaseController;
import com.manage.certificate.comm.exception.BizException;
import com.manage.certificate.comm.exception.BizExceptionEnum;
import com.manage.certificate.comm.exception.BizResponseEnum;
import com.manage.certificate.comm.model.Response;
import com.manage.certificate.entity.CertificateExam;
import com.manage.certificate.entity.CertificateExamStaff;
import com.manage.certificate.model.vo.CertificateExamStaffVO;
import com.manage.certificate.service.CertificateExamStaffService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class CertificateExamStaffController extends BaseController {

    @Resource
    private CertificateExamStaffService service;

    @PostMapping("/a/exam/staff/add")
    private Response addExamStaff(@Valid @RequestBody CertificateExamStaff examStaff) {
        if (examStaff != null) {
            service.save(examStaff);
            return super.successResponse(BizResponseEnum.SUCCEED_INSERT);
        }
        return super.failResponse(BizExceptionEnum.VALUE_ERROR);
    }

    @DeleteMapping("/a/exam/staff/del")
    private Response delExamStaff(String id) {
        service.delete(id);
        return super.successResponse(BizResponseEnum.SUCCEED_DELETE);
    }

    @PutMapping("/a/exam/staff/update")
    private Response updateExamStaff(@Valid @RequestBody CertificateExamStaff examStaff) {
        if (examStaff != null && examStaff.getId() != null) {
            service.save(examStaff);
            return super.successResponse(BizResponseEnum.SUCCEED_UPDATE);
        } else if (examStaff != null) {
            throw new BizException("错误，id为空，请确认值是否传递正确");
        }
        return super.failResponse(BizExceptionEnum.VALUE_ERROR);
    }

    @PostMapping("/a/exam/staff")
    private Response findList(@RequestBody CertificateExamStaffVO examStaff){
        PageInfo<CertificateExamStaff> list = service.findList(examStaff);
        return super.successResponse(BizResponseEnum.SUCCEED_QUERY,list);
    }

}
