package com.manage.certificate.comm.model;

import com.manage.certificate.comm.exception.BizException;
import com.manage.certificate.comm.exception.BizExceptionEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Copyright: Shanghai Smec Company.All rights reserved.
 * @Description:
 * @author: guohao.tan
 * @since: 2021/12/24 4:33 下午
 * @history: 1.2021/12/24 created by guohao.tan
 */
public class Response implements Serializable {
    public static final String CODE_OK = "ok";
    private String code;
    private String message;
    private Long total;
    private Object data;
    private String requestid;

    public Response() {
        this(CODE_OK, null);
    }

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
        this.requestid = UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static Response error(String message) {
        return new Response(BizExceptionEnum.SYS_ERROR_CODE.getCode(), message);
    }
    public static Response error(BizExceptionEnum bizExceptionEnum,String message){
        Response response = new Response(bizExceptionEnum.getCode(),bizExceptionEnum.getMessage());
        if(message!=null){
            response.setMessage(message);
        }
        return response;
    }

    public static Response error(BizException bizException) {
        return new Response(bizException.getCode(), bizException.getMessage());
    }

    public static Response sucess() {
        return new Response();
    }

    public Response data(Object data) {
        return this.setData(data);
    }

    public Response set(String field, String value) {
        if (this.data == null || !(this.data instanceof Map)) {
            //此处HashMap根据20个字段来进行初始化设置，一般覆盖了常用的长度
            this.data = new HashMap(27);
        }

        ((Map) this.data).put(field, value);
        return this;
    }

    public String getCode() {
        return this.code;
    }

    public Response setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    public Long getTotal() {
        return this.total;
    }

    public Response setTotal(Long total) {
        this.total = total;
        return this;
    }

    public Object getData() {
        return this.data;
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }

    public String getRequestid() {
        return this.requestid;
    }

    public Response setRequestid(String requestid) {
        this.requestid = requestid;
        return this;
    }
}
