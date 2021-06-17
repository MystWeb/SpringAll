package cn.myst.web.upload.file.controller;

import cn.myst.web.upload.file.common.utils.LogUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ziming.xing
 * Create Date：2020/12/25
 */
@RestController
@RequestMapping("/exception")
@Api(tags = "测试日志功能接口")
public class TestExceptionController {
    /**
     * 测试日志切面
     *
     * @return
     */
    @ApiOperation("测试日志切面的接口")
    @GetMapping("/aspect")
    public int aspect() {
        int i = 1 / 0;
        return i;
    }

    /**
     * 测试日志util
     */
    @ApiOperation("测试日志util的接口")
    @GetMapping("/util")
    public void util() {
        try {
            System.out.println(1 / 0);
        } catch (Exception e) {
            LogUtils.logToFile(e);
        }
    }
}
