package com.manage.certificate.comm.exception;


/**
 * @Copyright: Shanghai Smec Company.All rights reserved.
 * @Description: 业务异常基础类，继承自运行时类
 * @author: xiaowei.yao
 * @since: 2020/3/21 10:51 AM
 * @history: 1.2020/3/21 created by xiaowei.yao
 */
public class BizException extends RuntimeException{
    private String code;
    private String message;
    private BizExceptionEnum bizExceptionEnum;

    public BizException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BizException(String msg) {
        super(msg);
        this.message = msg;
    }

    public BizException(BizExceptionEnum bizExceptionEnum) {
        this.code = bizExceptionEnum.getCode();
        this.message = bizExceptionEnum.getMessage();
        this.bizExceptionEnum = bizExceptionEnum;
    }

    public BizExceptionEnum getBizExceptionEnum() {
        return this.bizExceptionEnum;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
