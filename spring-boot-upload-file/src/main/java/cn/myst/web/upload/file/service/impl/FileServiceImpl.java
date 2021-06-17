package cn.myst.web.upload.file.service.impl;

import cn.myst.web.upload.file.common.utils.FileUtils;
import cn.myst.web.upload.file.common.utils.UploadUtils;
import cn.myst.web.upload.file.config.UploadConfig;
import cn.myst.web.upload.file.mapper.FileMapper;
import cn.myst.web.upload.file.model.file.File;
import cn.myst.web.upload.file.service.IFileService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

/**
 * @author ziming.xing
 * Create Date：2020/12/25
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements IFileService {

    private final UploadConfig UploadConfig;

    private final FileMapper fileMapper;

    @Override
    public void upload(String name, String md5, MultipartFile file) throws IOException {
        String path = UploadConfig.getUploadPath() + FileUtils.generateFileName();
        FileUtils.write(path, file.getInputStream());
        File fileBuild = File.builder()
                .name(name)
                .md5(md5)
                .path(path)
//                .uploadTime(new Date())
                .build();
        fileMapper.insert(fileBuild);
    }

    @Override
    public void uploadWithBlock(String name, String md5, Long size, Integer chunks, Integer chunk, MultipartFile file) throws IOException {
        String fileName = UploadUtils.getFileName(md5, chunks);
        FileUtils.writeWithBlock(UploadConfig.getUploadPath() + fileName, size, file.getInputStream(), file.getSize(), chunks, chunk);
        UploadUtils.addChunk(md5, chunk);
        if (UploadUtils.isUploaded(md5)) {
            UploadUtils.removeKey(md5);
            File fileBuild = File.builder()
                    .name(fileName)
                    .md5(md5)
                    .path(UploadConfig.getUploadPath())
                    .build();
            fileMapper.insert(fileBuild);
        }
    }

    @Override
    public boolean checkMD5(String md5) {
        QueryWrapper<File> fileQueryWrapper = new QueryWrapper<>();
        fileQueryWrapper.lambda()
                .eq(File::getMd5, md5);
//        fileQueryWrapper.eq(File.COL_MD5, md5);
        return Objects.isNull(fileMapper.selectOne(fileQueryWrapper));
    }
}
