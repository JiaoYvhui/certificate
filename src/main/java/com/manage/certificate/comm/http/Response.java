package com.manage.certificate.comm.http;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Copyright: Shanghai Smec Company.All rights reserved.
 * @Description:统一响应对象
 * @author: xiaowei.yao
 * @since: 2020/3/21 10:58 AM
 * @history: 1.2020/3/21 created by xiaowei.yao
 */
public class Response implements Serializable {
    private String code;
    private String message;
    private Long total;
    private Object data;
    private String requestId;

    public Response() {
        this("ok", null);
    }

    public static Response page(Page<?> page) {
        Response response = new Response();
        response.data = page.getContent();
        response.total = page.getTotalElements();
        return response;
    }

    public Response(String code, String message) {
        this.code = "ok";
        this.code = code;
        this.message = message;
        this.requestId = UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static Response error(String message) {
        return new Response("error", message);
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }

    public Response data(Object data) {
        return this.setData(data);
    }


    public Response setTotal(Long total) {
        this.total = total;
        return this;
    }


    public static Response ok() {
        return new Response();
    }

    public Response set(String field, String value) {
        if (this.data == null || !(this.data instanceof Map)) {
            this.data = new HashMap(16);
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

    public Object getData() {
        return this.data;
    }


    public String getRequestId() {
        return this.requestId;
    }

    public Response setRequestId(String requestid) {
        this.requestId = requestid;
        return this;
    }
}
