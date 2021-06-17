package cn.myst.web.upload.file.controller;

import cn.myst.web.upload.file.service.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

/**
 * 大文件上传
 *
 * @author ziming.xing
 * Create Date：2020/12/25
 */
@Api(tags = "大文件上传相关接口")
@RestController
@RequestMapping("/BigFile")
@RequiredArgsConstructor
public class BigFileUploadController {

    private final IFileService fileService;

    @ApiOperation("大文件上传接口")
    @PostMapping("/")
    public void upload(String name,
                       String md5,
                       Long size,
                       Integer chunks,
                       Integer chunk,
                       MultipartFile file) throws IOException {
        if (Objects.nonNull(chunks) && chunks != 0) {
            fileService.uploadWithBlock(name, md5, size, chunks, chunk, file);
        } else {
            fileService.upload(name, md5, file);
        }
    }
}
