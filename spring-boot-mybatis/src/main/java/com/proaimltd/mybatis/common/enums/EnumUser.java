package com.proaimltd.mybatis.common.enums;

import com.proaimltd.mybatis.common.exception.IProStatus;
import org.apache.commons.lang3.StringUtils;

/**
 * 用户枚举类
 */
public enum EnumUser implements IProStatus {

    EXCEPTION_QUERY_USER(1001, "查询用户发生异常"),
    EXCEPTION_ADD_USER(1002, "创建用户发生异常"),
    EXCEPTION_DELETE_USER(1003, "删除用户发生异常"),
    EXCEPTION_UPDATE_USER(1004, "更新用户发生异常"),
    EXCEPTION_FIND_USER_BY_ID(1005, "根据ID获取用户详情发生异常"),

    FAILED_FIND_ALL_USER(1006, "用户列表返回失败"),
    FAILED_FIND_USER_BY_ID(1007, "根据ID没有找到用户"),

    EXIST_ROLE_NAME(1008, "用户已经存在");

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
    EnumUser(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessageByCode(String code) {
        for (EnumUser item : EnumUser.values()) {
            if (code.equals(item.code)) {
                return item.message;
            }
        }
        return null;
    }

    public static Integer getCodeByMessage(String message) {
        for (EnumUser item : EnumUser.values()) {
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
