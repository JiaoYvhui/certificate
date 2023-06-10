package com.manage.certificate.comm.config;

/**
 * @Copyright: Shanghai Smec Company.All rights reserved.
 * @Description:
 * @author: gezisong
 * @email gezisong@smec-cn.com
 * @Since: 2021/12/7 13:08
 * @history 2021/12/7 Created by gezisong
 */
public enum BpmOperationEnum {
    /**
     * 流程操作：审批通过
     */
    BPM_OPERATION_APPROVE("APPROVE","审批通过"),
    /**
     * 流程操作：退回
     */
    BPM_OPERATION_BACK("BACK","退回流程"),
    /**
     * 流程操作：发起
     */
    BPM_OPERATION_START("START","发起流程"),
    /**
     * 流程操作：关闭
     */
    BPM_OPERATION_REJECT("REJECT","关闭流程"),
    /**
     * 流程操作：保存
     */
    BPM_OPERATION_SAVE("SAVE","保存"),
    /**
     * 流程操作：结束流程，单据状态更改为COMPLETED
     */
    BPM_OPERATION_COMPLETED("COMPLETED","完成流程"),
    /**
     * 流程操作：草稿，单据状态更改为STORAGE
     */
    BPM_OPERATION_STORAGE("STORAGE","草稿"),

    EDITOR("EDITOR", "编辑") ;
    BpmOperationEnum(String code,String message){
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
        BpmOperationEnum[] enums = BpmOperationEnum.values();
        for (BpmOperationEnum item : enums) {
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
