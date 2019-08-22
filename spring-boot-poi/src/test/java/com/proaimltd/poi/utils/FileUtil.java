package com.proaimltd.poi.utils;

import java.io.InputStream;

/**
 * @author: ziming.xing
 * @date: 2019/7/29 14:41
 */
public class FileUtil {
    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }
}
