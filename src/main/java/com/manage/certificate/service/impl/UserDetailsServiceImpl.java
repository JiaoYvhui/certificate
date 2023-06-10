package com.manage.certificate.service.impl;

import com.manage.certificate.comm.exception.BizException;
import com.manage.certificate.entity.CertificateUser;
import com.manage.certificate.mapper.CertificateUserMapper;
import com.manage.certificate.util.LoginUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private CertificateUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询角色信息
        CertificateUser user = userMapper.getLoginPhone(username);

        /**
         * 查询不到用户信息抛出异常
         * SpringSecurity可以处理
         */
        if (Objects.isNull(user)){
            throw new BizException("用户名或密码错误");
        }

        return new LoginUser(user);
    }


}
