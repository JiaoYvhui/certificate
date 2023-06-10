package com.manage.certificate.comm.exception;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @description: 业务异常枚举类
 * @author: yaoshallwe
 * @create: 12/11/2019 12:05
 */
public enum BizExceptionEnum {
    /**
     * 系统错误
     */
    SYS_ERROR_CODE("5000", "系统错误"),
    /**
     * 用户token校验错误
     */
    TOKEN_WRONG("5000", "登录失效，请重新登录"),
    /**
     *获取token用户信息错误
     */
    TOKEN_USER_ERROR("5000","获取当前用户信息失败，请联系管理员或稍后再试！"),
    /**
     * 请求参数未携带token信息
     */
    NO_TOKEN("5000", "未携带token，不给予请求权限"),
    /**
     *未查询到数据
     */
    QUERY_DATA_EXCEPTION("5000", "未查询出数据"),
    /**
     * 上传失败，请选择文件
     */
    UPLOAD_DATA_ERROR("5000","上传失败，请选择文件"),
    /**
     * 上传失败
     */
    UPLOAD_ERROR("5000","上传失败，文件过大，请联系技术人员"),
    /**
     * 实体类值错误
     */
    VALUE_ERROR("5000", "错误，实体类为空，请确认值是否传递正确"),
    /**
     * 下载路径为空
     */
    DOWNLOAD_ROUTE_ERROR("5000","下载失败，下载路径为空");

    private String code;

    private String message;

    BizExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public String getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }
}