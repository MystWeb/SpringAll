package com.proaimltd.mybatis.common;

/**
 * @author: ziming.xing
 * @date: 2019/7/12 10:26
 */
public abstract class BaseConstants {
    public static final String VERSION = "1.0";

    public static final String DOT = ".";

    public static final String UNDERLINE = "_";
    //重试次数
    public static final int MAX_RETRY_TIMES = 5;
    //初次重试时间间隔，5秒
    public static final int RETRY_BACK_OFF = 5 * 1000;

    public static final Boolean IS_DEBUG = true;

    public static final String ALLOW_UPLOAD_FILE_MIME_TYPES = "image/jpg|image/jpeg|image/gif|image/png";

    public static final double ALLOW_UPLOAD_FILE_MAX_SIZE = 5.0D; //容许最大上传的图片大小，单位：MB

    public static final String YMDHMS = "^\\d\\d\\d\\d-(0?[1-9]|1[0-2])-(0?[1-9]|[12][0-9]|3[01]) (00|[0-9]|1[0-9]|2[0-3]):([0-9]|[0-5][0-9]):([0-9]|[0-5][0-9])$";

    public static final String MIN_TIME = " 00:00:00";

    public static final String MAX_TIME = " 23:59:59";

    public static final String MIN = "min";

    public static final String MAX = "max";

    public static final String HH_MM = "HH:mm";

    public static final String YYYY_MM = "yyyy-MM";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String STRIKE_LINE = "-";

    public static final String VERTICAL_LINE = "|";
}
