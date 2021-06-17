package cn.myst.web.upload.file.controller;

/**
 * @author ziming.xing
 * Create Date：2020/12/25
 */

import cn.myst.web.upload.file.service.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
@Api(tags = "文件上传相关接口")
@RequiredArgsConstructor
public class FileUploadController {

    private final IFileService fileService;

    @ApiOperation("文件上传接口")
    @PostMapping("/")
    public void upload(String name,
                       String md5,
                       MultipartFile file) throws IOException {
        fileService.upload(name, md5,file);
    }
}
