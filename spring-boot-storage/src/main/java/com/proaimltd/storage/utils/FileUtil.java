package com.proaimltd.storage.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
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
 * 文件上传与下载工具类
 *
 * @author: ziming.xing
 * @date: 2019/9/2 11:53
 */
@Slf4j
public class FileUtil {

    /**
     * 单个文件上传
     *
     * @param file     文件
     * @param filePath 指定文件存储的路径
     * @return 上传结果
     */
    public static String singleFileupload(MultipartFile file, String filePath) {
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
        if (StringUtils.isEmpty(filePath)) {
            filePath = "D://";
        }

        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + "|+=|-|" + fileName;

        File dest = new File(filePath + fileName);

        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            boolean isMkdir = dest.getParentFile().mkdirs();
        }

        try {
            file.transferTo(dest);
            // 上传成功则返回绝对路径
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
     * @param request  HttpServletRequest
     * @param file     文件集
     * @param filePath 指定文件存储的路径
     * @return 上传结果
     */
    public String multiFileupload(HttpServletRequest request, String file, String filePath) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles(file);

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
            if (StringUtils.isEmpty(filePath)) {
                filePath = "D://";
            }

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
     * @param response HttpServletResponse
     * @param fileName 要下载的文件名
     * @param filePath 文件所在的路径
     * @return 下载结果
     */
    public static String downLoadFile(/*HttpServletRequest request, */HttpServletResponse response, String fileName, String filePath) {
        // 文件名可以从request中获取
        // 获取项目路径
        // String filePath = request.getServletContext().getRealPath("/");

        // 要下载的文件名
        if (StringUtils.isEmpty(fileName)) {
            return "文件名不可为空";
        }

        // 文件所在路径
        if (StringUtils.isEmpty(filePath)) {
            filePath = "D://";
        }
        File file = new File(filePath, fileName);
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
