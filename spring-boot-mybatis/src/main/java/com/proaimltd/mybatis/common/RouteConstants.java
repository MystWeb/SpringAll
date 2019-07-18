package com.proaimltd.mybatis.common;

/**
 * @author: ziming.xing
 * @date: 2019/7/12 10:26
 * @description：路由规划 所有的URL必须符合RESTful风格, GET/POST/PUT/DELETE
 */
public abstract class RouteConstants extends BaseConstants {
    public static final String URL_PREFIX = "/api";

    public static final String URL_VERSION = "/" + VERSION;

    //---------------------管理端开始---------------------
    public static final String ADMIN_URL = "/admin";
    //工作流管理
    public static final String WORKFLOW_URL = ADMIN_URL + "/workflow";
    //实例
    public static final String WORKFLOW_INSTANCE_URL = ADMIN_URL + "/workflow/instance";
    //模板
    public static final String WORKFLOW_TEMPLATE_URL = ADMIN_URL + "/workflow/template";
    //模板资源
    public static final String WORKFLOW_TEMPLATE_RESOURCE_URL = ADMIN_URL + "/workflow/template/resource";

    //用户管理
    public static final String USER_URL = ADMIN_URL + "/user";
    //授权authorization
    public static final String USER_AUTHORIZATION_URL = ADMIN_URL + "/user/authorization";
    //站点管理
    public static final String TENANT_URL = ADMIN_URL + "/tenant";
    //用户组管理
    public static final String USER_GROUP_URL = ADMIN_URL + "/group";
    //角色管理
    public static final String ROLE_URL = ADMIN_URL + "/role";
    //角色授权
    public static final String ROLE_AUTHORIZATON_URL = ADMIN_URL + "/role/authorization";
    //授权管理
    public static final String PERMISSION_URL = ADMIN_URL + "/permission";
    //数据源管理
    public static final String DATA_SOURCE_URL = ADMIN_URL + "/data-source";
    //菜单管理
    public static final String MENU_URL = ADMIN_URL + "/menu";
    //---------------------管理端结束---------------------
    //---------------------用户端开始---------------------
    //用户中心
    public static final String USER_CENTER_URL = "/user";
    //密码管理
    public static final String PASSWORD_URL = "/password";
    //密码找回
    public static final String PASSWORD_RECOVERY_URL = PASSWORD_URL + "/recovery";
    //用户登录
    public static final String USER_SIGN_IN_URL = USER_CENTER_URL + "/signIn";
    //用户登出
    public static final String USER_SIGN_OUT_URL = USER_CENTER_URL + "/signOut";

    //个人工作台
    public static final String PROFILE_URL = USER_CENTER_URL + "/profile";
    //发起流程审批
    public static final String PROCESS_URL = USER_CENTER_URL + "/process";
    //流程类型
    public static final String PROCESS_TYPES_URL = USER_CENTER_URL + "/process-types";
    //我的任务
    public static final String TASKS_URL = USER_CENTER_URL + "/tasks";
    //任务详情
    public static final String TASK_URL = USER_CENTER_URL + "/task";
    //流程审批
    public static final String TASK_APPROVE_URL = USER_CENTER_URL + "/task/approve";
    //我的消息
    public static final String MESSAGES_URL = USER_CENTER_URL + "/messages";
    //消息详情
    public static final String MESSAGE_URL = USER_CENTER_URL + "/message";
    //报表管理
    public static final String REPORT_URL = "/report";
    //文件管理
    public static final String FILE_URL = "/file";
    //---------------------用户端结束---------------------
}
