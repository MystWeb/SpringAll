package cn.myst.web.upload.file.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ziming.xing
 * Create Date：2021/2/20
 */
@Api(tags = "测试接口")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class TestController {

    @ApiOperation("测试C++联调")
    @PostMapping("/test")
    public Message getMessage(){
        return new Message();
    }
}
@Data
class Message{
    private Boolean success = true;
    private String msg = "成功访问到Post接口";
}
