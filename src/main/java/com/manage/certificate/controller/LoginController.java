package com.manage.certificate.controller;

import com.github.pagehelper.PageInfo;
import com.manage.certificate.comm.controller.BaseController;
import com.manage.certificate.comm.exception.BizResponseEnum;
import com.manage.certificate.comm.model.Response;
import com.manage.certificate.entity.CertificateUser;
import com.manage.certificate.model.dto.LoginDto;
import com.manage.certificate.model.vo.CertificateUserVO;
import com.manage.certificate.service.CertificateUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class LoginController extends BaseController {
    @Resource
    private CertificateUserService userService;

    @PostMapping("/a/loginByPassword")
    public Response tologin(@RequestBody LoginDto loginDto) {
        return super.successResponse(BizResponseEnum.LOGIN_SUCCEED_GET, userService.loginByPassword(loginDto));
    }

    @PostMapping("/a/logout")
    public Response logout() {
        return super.successResponse(BizResponseEnum.LOGIN_SING_OUT, userService.logout());
    }

    @ResponseBody
    @PostMapping("/a/user/add")
    public Response addUser(@Valid @RequestBody CertificateUser user) {
        userService.save(user);
        return super.successResponse(BizResponseEnum.SUCCEED_REGISTRATION);
    }

    @ResponseBody
    @PutMapping("/a/user/update")
    public Response updateUser(@RequestBody CertificateUser user) {
        userService.updatePhone(user);
        return super.successResponse(BizResponseEnum.SUCCEED_UPDATE);
    }

    @DeleteMapping("/a/user/del")
    public Response deleteUser(String id) {
        userService.delete(id);
        return super.successResponse(BizResponseEnum.SUCCEED_DELETE);
    }

    @PutMapping("/a/user/rebuild")
    public Response rebuildUserPassword(@Valid @RequestBody CertificateUserVO user) {
        userService.rebuildPassword(user);
        return super.successResponse(BizResponseEnum.SUCCEED_UPDATE);
    }

    @PostMapping("/a/user")
    public Response findPage(@RequestBody CertificateUser certificateUser) {
        CertificateUser userId = userService.getUserId();
        if ("管理员".equals(userId.getRole())) {
            PageInfo<CertificateUser> page = userService.findPage(certificateUser);
            return super.successResponse(BizResponseEnum.SUCCEED_QUERY, page);
        }
        return super.successResponse(BizResponseEnum.SUCCEED_QUERY, null);
    }

}
