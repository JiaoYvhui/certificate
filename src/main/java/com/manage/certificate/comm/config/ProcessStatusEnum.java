package com.manage.certificate.comm.config;

/**
 * @Copyright: Shanghai Smec Company.All rights reserved.
 * @Description: 流程状态定义
 * @author: gezisong
 * @email gezisong@smec-cn.com
 * @Since: 2021/7/21 13:18
 * @history 2021/7/21 Created by gezisong
 */
public enum ProcessStatusEnum {

    /**
     * 流程：草稿
     */
    Process_STATUS_NEW("new","草稿"),
    /**
     * 流程：等待处理
     */
    Process_STATUS_PROCESSED("processed","等待处理"),
    /**
     * 流程：处理中
     */
    Process_STATUS_PROCESSING("processing","处理中"),
    /**
     * 流程：等待领取
     */
    Process_STATUS_RECEIVE("receive","等待领取"),
    /**
     * 流程：领取中
     */
    Process_STATUS_RECEIVING("receiving","已领取"),
    /**
     * 流程：已拒绝
     */
    Process_STATUS_REJECT("reject", "已拒绝");


    ProcessStatusEnum(String code, String message){
        this.code = code;
        this.message = message;
    }
    private String code;
    private String message;
    /**
     * 输入枚举的code，返回message
     * @param code
     * @return
     */
    public static String getMsgByCode(String code) {
        ProcessStatusEnum[] enums = ProcessStatusEnum.values();
        for (ProcessStatusEnum item : enums) {
            if (item.code.equals(code)) {
                return item.message;
            }
        }
        return null;
    }
    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
