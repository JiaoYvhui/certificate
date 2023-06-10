package com.manage.certificate.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manage.certificate.comm.exception.BizException;
import com.manage.certificate.comm.exception.BizResponseEnum;
import com.manage.certificate.entity.CertificateUser;
import com.manage.certificate.mapper.CertificateUserMapper;
import com.manage.certificate.model.dto.LoginDto;
import com.manage.certificate.model.dto.UserDto;
import com.manage.certificate.model.vo.CertificateUserVO;
import com.manage.certificate.service.CertificateUserService;
import com.manage.certificate.util.JwtUtil;
import com.manage.certificate.util.LoginUser;
import com.manage.certificate.util.RedisConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class CertificateUserServiceImpl implements CertificateUserService {

    private static final Logger logger = LoggerFactory.getLogger(CertificateUserServiceImpl.class);


    @Resource
    private CertificateUserMapper mapper;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<CertificateUser> findAll() {
        return mapper.findAll();
    }

    @Override
    public Map<String, Object> loginByPassword(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getPhone(), loginDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 如果认证没通过 则给出相应提示
        if (ObjectUtil.isEmpty(authenticate)) {
            logger.info("message：{}", BizResponseEnum.LOGIN_FAILURE_GET);
            throw new BizException("用户名或密码错误！");
        }
        // 认证通过，使用 userId 生成一个 Jwt,Jwt存入Result返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String user_id = loginUser.getUser().getId().toString();
        CertificateUser user = loginUser.getUser();
        String jwtToken = JwtUtil.createJWT(user_id);

        // TODO 使用 StringRedisTemplate存储实体类型，用hash 不过需将实体转为Map
        UserDto userDto_redis = BeanUtil.copyProperties(user, UserDto.class);
        // TODO 由于 StringRedisTemplate要求所有字段都是String类型，而UserDto的id是long类型
        // TODO 所以需要把所有字段都修改为String类型
        Map<String, Object> map = BeanUtil.beanToMap(userDto_redis, new HashMap<>(),
                CopyOptions.create().setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));

        // 把用户信息存储到Redis中 userid作为Key的前缀
        String login_key = RedisConstants.LOGIN_USER_KEY + user_id;
        stringRedisTemplate.opsForHash().putAll(login_key, map);

        // TODO 5.4 设置有效期
        stringRedisTemplate.expire(login_key, RedisConstants.LOGIN_USER_TTL, TimeUnit.SECONDS);

        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("authorization", jwtToken);
        hashMap.put("user",loginUser.getUser());
        return hashMap;
    }

    @Override
    public String logout() {
        /**
         * 1. 从 SecurityContextHolder 中获取用户信息(ID)
         * 2. 从Redis中删除对应的Key
         */
        UsernamePasswordAuthenticationToken authenticationToken
                = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authenticationToken.getPrincipal();
        Long userId = userDto.getId();
        // 根据Key删除Redis的值
        stringRedisTemplate.delete(RedisConstants.LOGIN_USER_KEY + userId);
        return "退出成功";
    }

    @Override
    public void save(CertificateUser user) {
        if (user != null) {
            CertificateUser byPhone = mapper.findByPhone(user.getPhone(), 0);
            if (byPhone != null) {
                throw new BizException("该账号已存在");
            }
            CertificateUser certificateUser = mapper.findByPhone(user.getPhone(), 1);
            if (certificateUser != null) {
                mapper.recovery(certificateUser.getId());
                user.setId(certificateUser.getId());
                user.preInsert();
            }
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if (user.getId() != null) {
                mapper.update(user);
            } else {
                user.preInsert();
                mapper.insert(user);
            }
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
    public void rebuildPassword(CertificateUserVO user) {
        if (user != null && user.getId() != null) {
            CertificateUser certificateUser = mapper.get(user.getId());
            if (certificateUser == null) {
                throw new BizException("未找到id为" + user.getId() + "的值，请确认id传递是否正确");
            }
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            mapper.updatePassword(user);
        } else if (user != null) {
            throw new BizException("错误，id为空，请确认值是否传递正确");
        } else {
            throw new BizException("错误，实体类为空，请确认值是否传递正确");
        }
    }

    @Override
    public PageInfo<CertificateUser> findPage(CertificateUser certificateUser) {
        if (certificateUser.getPageNum() == null || certificateUser.getPageNum() < 1) {
            certificateUser.setPageNum(1);
        }
        if (certificateUser.getPageSize() == null || certificateUser.getPageSize() < 1) {
            certificateUser.setPageSize(10);
        }
        PageHelper.startPage(certificateUser.getPageNum(), certificateUser.getPageSize());
        return new PageInfo<>(mapper.findList(certificateUser));
    }

    @Override
    public void updatePhone(CertificateUser user) {
        if (user != null && user.getId() != null) {
            CertificateUser certificateUser = mapper.get(user.getId());
            if (certificateUser == null) {
                throw new BizException("未找到id为" + user.getId() + "的值，请确认id传递是否正确");
            }
            mapper.updatePhone(user);
        } else if (user != null) {
            throw new BizException("错误，id为空，请确认值是否传递正确");
        } else {
            throw new BizException("错误，实体类为空，请确认值是否传递正确");
        }
    }

    public CertificateUser getUserId(){
        UsernamePasswordAuthenticationToken authenticationToken
                = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authenticationToken.getPrincipal();
        return mapper.get(Math.toIntExact(userDto.getId()));
    }
}
