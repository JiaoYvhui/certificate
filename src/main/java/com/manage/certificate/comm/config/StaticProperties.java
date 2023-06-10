package com.manage.certificate.comm.config;

/**
 * @Copyright: Shanghai Smec Company.All rights reserved.
 * @Description: 系统常量清单
 * @author: guohao.tan
 * @since: 2021/12/24 4:40 下午
 * @history: 1.2021/12/24 created by guohao.tan
 */
public interface StaticProperties {

    /**
     * 项目控制层path
     */
    String BASE_END_POINT = "/asr/v1";


    /**
     * 同步用户接口数据库配置
     */
//    String URL_USER = "jdbc:mysql://118.25.189.220:3306/sapmanager2";
//    String USERNAME_USER = "root";
//    String PASSWORD_USER = "PassWord123!@";

    String URL_USER = "jdbc:mysql://10.136.72.12:3306/dbsfmanager";
    String USERNAME_USER = "root";
    String PASSWORD_USER = "PassWord123!@";
    /**
     * 查询人员表
     */
    String SQL_USER = "select * from sys_user";
    /**
     * 查询角色表
     */
    String SQL_ROLE = "select * from sys_role";
    /**
     * 查询人员角色表
     */
    String SQL_USER_ROLE = "select * from sys_user_role";


    /**
     * 同步用户接口数据库配置
     */
    String URL = "jdbc:mysql://118.25.189.220:3306/sapmanager2";
    String USERNAME = "root";
    String PASSWORD = "PassWord123!@";
    String SQL = "select * from sys_user";


    /**
     * 发邮件接口地址
     */
//    String MAIL_URL = "http://118.25.189.220:8080/sapmanger/a/sys/systemConfig/outSendEmail";
    String MAIL_URL = "http://10.136.72.5/a/sys/systemConfig/outSendEmail";
}
