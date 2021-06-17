package cn.myst.web.upload.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author ziming.xing
 * Create Date：2020/12/25
 */
public interface IFileService {

    /**
     * 上传文件
     *
     * @param name 文件名
     * @param md5  MD5
     * @param file 文件
     * @throws IOException IOException
     */
    void upload(String name,
                String md5,
                MultipartFile file) throws IOException;

    /**
     * 分块上传文件
     *
     * @param name   文件名
     * @param md5    MD5
     * @param size   大小
     * @param chunks 文件分块数
     * @param chunk  文件分块序号
     * @param file   文件
     * @throws IOException IOException
     */
    void uploadWithBlock(String name,
                         String md5,
                         Long size,
                         Integer chunks,
                         Integer chunk,
                         MultipartFile file) throws IOException;

    /**
     * 检查MD5判断文件是否已上传
     * true:  未上传
     * false: 已上传
     *
     * @param md5 MD5
     * @return boolean
     */
    boolean checkMD5(String md5);
}
