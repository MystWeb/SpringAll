package cn.myst.web.upload.file.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author ziming.xing
 * Create Date：2020/12/25
 */
@Slf4j
@RestController
@RequestMapping("/files")
@Api(tags = "文件上传与下载管理")
public class FileNoDBController {
    /**
     * 单个文件上传
     *
     * @return 上传结果
     */
    @PostMapping("/uploadSingleFile")
    @ApiOperation(value = "单文件上传", notes = "一个文件上传")
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "文件为空";
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        log.info("上传的文件名为：" + fileName);

        // 获取文件的后缀名
        assert fileName != null;
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info("上传的后缀名为：" + suffixName);

        // 文件上传路径
        String filePath = "D://";

        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + "|+=|-|" + fileName;

        File dest = new File(filePath + fileName);

        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            boolean isMkdir = dest.getParentFile().mkdirs();
        }

        try {
            file.transferTo(dest);
            // 文件上传成功的绝对路径
            log.info(dest.getCanonicalPath());
            return dest.getCanonicalPath();
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    /**
     * 多文件上传
     * 类似单文件上传, 遍历
     *
     * @return 上传结果
     */
    @PostMapping("/uploadMultipleFiles")
    @ApiOperation(value = "多文件上传", notes = "多个文件上传")
    public String handleMultiFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

        for (MultipartFile multipartFile : files) {
            if (multipartFile.isEmpty()) {
                return "文件是空的";
            }

            // 读取文件内容并写入 指定目录中
            String fileName = multipartFile.getOriginalFilename();
            // String suffixName =
            // fileName.substring(fileName.lastIndexOf("."));
            fileName = UUID.randomUUID() + "|+=|-|" + fileName;

            // 文件上传路径
            String filePath = "D:/test/";

            File dest = new File(filePath + fileName);
            // 判断目录是否存在
            if (!dest.getParentFile().exists()) {
                boolean isMkdir = dest.getParentFile().mkdirs();
            }

            try {
                multipartFile.transferTo(dest);
            } catch (IOException e) {
                return "后台也不知道为什么, 反正就是上传失败了";
            }
        }
        return "上传成功";
    }

    /**
     * 文件下载
     *
     * @return 下载结果
     */
    @GetMapping
    @ApiOperation(value = "文件下载", notes = "单个文件下载")
    public String downLoadFile(HttpServletRequest request, HttpServletResponse response) {
        // 文件名可以从request中获取, 这儿为方便, 写死了
        String fileName = "demo.zip";
        // 获取项目路径
        // String path = request.getServletContext().getRealPath("/");
        String path = "D:/test/";
        File file = new File(path, fileName);
        if (file.exists()) {
            // 设置强制下载打开
            response.setContentType("application/force-download");
            // 文件名乱码, 使用new String() 进行反编码
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);

            // 读取文件
            BufferedInputStream bi = null;
            try {
                byte[] buffer = new byte[1024];
                bi = new BufferedInputStream(new FileInputStream(file));
                ServletOutputStream outputStream = response.getOutputStream();
                int i = -1;
                while (-1 != (i = bi.read(buffer))) {
                    outputStream.write(buffer, 0, i);
                }
                outputStream.flush();
                return "下载成功";
            } catch (Exception e) {
                e.printStackTrace();
                return "程序猿真不知道为什么, 反正就是下载失败了";
            } finally {
                if (bi != null) {
                    try {
                        bi.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "文件不存在";
    }
}
