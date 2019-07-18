package com.proaimltd.mybatis.common.enums;

import com.proaimltd.mybatis.common.exception.IProStatus;
import org.apache.commons.lang3.StringUtils;

/**
 * 角色枚举类
 *
 * @author: ziming.xing
 * @date: 2019/7/12 10:34
 */
public enum EnumRole implements IProStatus {

    EXCEPTION_QUERY_ROLE(2001, "查询角色发生异常"),
    EXCEPTION_ADD_ROLE(2002, "创建角色发生异常"),
    EXCEPTION_DELETE_ROLE(2003, "删除角色发生异常"),
    EXCEPTION_UPDATE_ROLE(2004, "更新角色发生异常"),
    EXCEPTION_FIND_ROLE_BY_ID(2005, "根据ID获取角色详情发生异常"),

    FAILED_FIND_ALL_ROLE(2006, "角色列表返回失败"),
    FAILED_FIND_ROLE_BY_ID(2007, "根据ID没有找到角色"),

    EXIST_ROLE_NAME(2008, "角色已经存在");

    /**
     * 错误代码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 构造器
     *
     * @param code
     * @param message
     */
    EnumRole(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessageByCode(String code) {
        for (EnumRole item : EnumRole.values()) {
            if (code.equals(item.code)) {
                return item.message;
            }
        }
        return null;
    }

    public static Integer getCodeByMessage(String message) {
        for (EnumRole item : EnumRole.values()) {
            if (StringUtils.equals(message, item.message)) {
                return item.code;
            }
        }
        return null;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
