package com.proaimltd.mybatis.common.enums;

import com.proaimltd.mybatis.common.exception.IProStatus;
import org.apache.commons.lang3.StringUtils;

/**
 * 用户信息枚举类
 */
public enum EnumInfo implements IProStatus {

    EXCEPTION_QUERY_INFO(3001, "查询用户信息发生异常"),
    EXCEPTION_ADD_INFO(3002, "创建用户信息发生异常"),
    EXCEPTION_DELETE_INFO(3003, "删除用户信息发生异常"),
    EXCEPTION_UPDATE_INFO(3004, "更新用户信息发生异常"),
    EXCEPTION_FIND_INFO_BY_ID(3005, "根据ID获取用户信息详情发生异常"),

    FAILED_FIND_ALL_INFO(3006, "用户信息列表返回失败"),
    FAILED_FIND_INFO_BY_ID(3007, "根据ID没有找到用户信息"),

    EXIST_INFO_NAME(3008, "用户信息已经存在");

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
    EnumInfo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessageByCode(String code) {
        for (EnumInfo item : EnumInfo.values()) {
            if (code.equals(item.code)) {
                return item.message;
            }
        }
        return null;
    }

    public static Integer getCodeByMessage(String message) {
        for (EnumInfo item : EnumInfo.values()) {
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
