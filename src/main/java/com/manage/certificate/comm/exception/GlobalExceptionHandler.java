package com.manage.certificate.comm.exception;

import com.manage.certificate.comm.http.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * @Copyright: Shanghai Smec Company.All rights reserved.
 * @Description:
 * @author: wenbo.liao
 * @since: 2020/3/21 10:52 AM
 * @history: 1.2020/3/21 created by wenbo.liao
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 拦截数字转换异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NumberFormatException.class)
    public Response notFount(NumberFormatException e) {
        e.printStackTrace();
        log.error(e.getMessage(), e);
        return Response.error("请输入数字");
    }

    /**
     * 登录时的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public Response notFount(InternalAuthenticationServiceException e) {
        e.printStackTrace();
        log.error(e.getMessage(), e);
        return Response.error(e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public Response notFount(BadCredentialsException e){
        e.printStackTrace();
        log.error(e.getMessage(), e);
        return Response.error(e.getMessage());
    }

    /**
     * 拦截无法反序列化异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response notFount(HttpMessageNotReadableException e) {
        e.printStackTrace();
        log.error(e.getMessage(), e);
        return Response.error("字段类型填写错误");
    }

    /**
     * 拦截SQL异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(SQLException.class)
    public Response notFount(SQLException e) {
        e.printStackTrace();
        log.error(e.getMessage(), e);
        return Response.error("数据库错误，请联系管理员");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Response notFount(RuntimeException e) {
        e.printStackTrace();
        log.error(e.getMessage(), e);
        return Response.error("运行时异常，请联系管理员");
    }

    /**
     * 系统异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage(), e);
        return Response.error("服务器错误，请联系管理员");
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Response handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        e.printStackTrace();
        return Response.error("不支持' " + e.getMethod() + "'请求");
    }


    /**
     * 校验注解参数异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response exceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMesssage = "";
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage += fieldError.getDefaultMessage() + "!";
        }
        e.printStackTrace();
        return Response.error(errorMesssage);
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BizException.class)
    public Response businessException(BizException e) {
        log.error(e.getMessage(), e);
        e.printStackTrace();
        return Response.error(e.getMessage());
    }

    /**
     * 乐观锁异常
     */
    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public Response optimisticLockingFailureException(ObjectOptimisticLockingFailureException e) {
        log.error("乐观锁异常：{}", e);
        e.printStackTrace();
        return Response.error("其他用户已修改申请，请F5刷新本页面");
    }
}