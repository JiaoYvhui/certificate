package com.manage.certificate.comm.exception;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description: 返回消息定义
 * @author: yaoshallwe
 * @create: 12/11/2019 12:05
 */
public enum BizResponseEnum {
    /**
     * 用户操作正常结果值
     */
    SUCCEED_OPERATION("2000", "操作成功"),
    /**
     * 查询数据正常返回结果值
     */
    SUCCEED_QUERY("2000", "查询成功"),
    /**
     * 同步数据操作
     */
    SUCCEED_SYNCHRONIZATION("2000", "同步成功"),
    /**
     * 插入数据操作
     */
    SUCCEED_INSERT("2000", "插入成功"),
    /**
     * 删除数据操作
     */
    SUCCEED_DELETE("2000", "删除成功"),
    /**
     * 更新数据操作
     */
    SUCCEED_UPDATE("2000", "修改成功"),
    /**
     * GET请求获取数据返回正常结果
     */
    SUCCEED_GET("2000", "获取成功"),
    /**
     * GET请求获取数据返回正常结果
     */
    LOGIN_SUCCEED_GET("2000", "登录成功"),
    /**
     * GET请求获取数据返回正常结果
     */
    LOGIN_FAILURE_GET("5000", "登录失败"),
    /**
     * 退出登录
     */
    LOGIN_SING_OUT("2000", "注销成功"),
    /**
     * 注册成功
     */
    SUCCEED_REGISTRATION("2000","账号注册成功"),
    /**
     * 注册失败
     */
    ERROR_REGISTRATION("5000","注册失败"),
    /**
     * POST请求正常返回结果
     */
    SUCCEED_POST("2000", "提交数据成功"),
    /**
     * 发送数据正常返回结果
     */
    SUCCEED_SEND("2000", "发送成功"),
    /**
     * 流程操作提示
     */
    SUCCEED_APPROVE("2000", "流程已提交"),
    /**
     * 流程操作提示
     */
    SUCCEED_REJECT("2000", "流程已关闭"),
    /**
     * 流程操作提示
     */
    SUCCEED_START("2000", "流程已发起"),
    /**
     * 流程操作提示
     */
    SUCCEED_BACK("2000", "流程已退回"),
    /**
     * 数据保存提示
     */
    SUCCEED_SAVE("2000", "保存成功"),

    SUCCEED_IMPORT("2000", "导入成功"),

    SUCCEED_EXPORT("2000", "导出成功"),
    /**
     * 上传成功
     */
    UPLOAD_SUCCEED("2000", "上传成功"),
    /**
     * 上传失败
     */
    UPLOAD_ERROR("5000", "上传失败"),
    /**
     * 下载成功
     */
    DOWNLOAD_SUCCEED("2000","下载成功");

    private String code;

    private String message;

    BizResponseEnum(String code, String message) {
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
