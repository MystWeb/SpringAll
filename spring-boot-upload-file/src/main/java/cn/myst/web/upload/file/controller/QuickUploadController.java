package cn.myst.web.upload.file.controller;

import cn.myst.web.upload.file.service.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 秒传
 *
 * @author ziming.xing
 * Create Date：2020/12/25
 */
@Api(tags = "文件秒传相关接口")
@RestController
@RequestMapping("/quickUpload")
@RequiredArgsConstructor
public class QuickUploadController {

    private final IFileService fileService;

    @ApiOperation("文件秒传接口")
    @GetMapping("/")
    public boolean upload(String md5) {
        return fileService.checkMD5(md5);
    }
}
