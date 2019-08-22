package com.proaimltd.poi.service.impl;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.proaimltd.poi.model.AuthResource;
import com.proaimltd.poi.model.ResourceTemplate;
import com.proaimltd.poi.model.dto.AuthResourceImportRespDTO;
import com.proaimltd.poi.service.IResourceService;
import com.proaimltd.poi.utils.EasyExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.Tika;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.proaimltd.poi.constants.BaseConstants.ALLOW_UPLOAD_EXCEL_FILE_MIME_TYPES;
import static com.proaimltd.poi.constants.ConstantsFile.EXCEPTION_UPLOAD_FILE_MIME_TYPE;
import static com.proaimltd.poi.constants.ConstantsFile.FAILED_UPLOAD_FILE;
import static com.proaimltd.poi.constants.ContantsExcel.EXCEPTION_EMPTY_EXCEL;
import static com.proaimltd.poi.constants.ContantsExcel.EXCEPTION_READER_EXCEL;

/**
 * @author: ziming.xing
 * @date: 2019/8/22 10:34
 */
@Slf4j
@Service
public class ResourceServiceImpl implements IResourceService {

    @Override
    public AuthResourceImportRespDTO importPubResources(MultipartFile file) {
        InputStream inputStream = null;
        if (file == null) {
            log.error(FAILED_UPLOAD_FILE);
        }
        // 校验上传的文件类型
        assert file != null;
        String mimeType = detectFileMimeType(file);
        if (StringUtils.isBlank(mimeType) || !mimeType.matches(ALLOW_UPLOAD_EXCEL_FILE_MIME_TYPES)) {
            log.error(EXCEPTION_UPLOAD_FILE_MIME_TYPE);
        }
        try {
            inputStream = new BufferedInputStream(file.getInputStream());
            // 读取文件
            List<ResourceTemplate> resourceTemplates = EasyExcelUtil.readExcelWithModel(inputStream, ResourceTemplate.class, ExcelTypeEnum.XLSX);
            // 校验Excel是否为空
            if (resourceTemplates.isEmpty()) {
                log.error(EXCEPTION_EMPTY_EXCEL);
            }
            // 保存数据库并记录行数
            AtomicInteger result = new AtomicInteger();
            resourceTemplates.forEach(resourceTemplate -> {
                AuthResource resource = new AuthResource();
                BeanUtils.copyProperties(resourceTemplate, resource);
                /*result.addAndGet(resourceMapper.insertResource(resource));*/
                result.addAndGet(1);
            });
            AuthResourceImportRespDTO respDTO = new AuthResourceImportRespDTO();
            respDTO.setResourceTemplates(resourceTemplates);
            respDTO.setResult(result);
            return respDTO;
        } catch (Exception e) {
            log.error(EXCEPTION_READER_EXCEL, e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 使用apache Tika检测文件的真实类型
     *
     * @param file 文件
     * @return 文件真实类型
     */
    private String detectFileMimeType(MultipartFile file) {
        Tika tika = new Tika();
        String format = null;
        try {
            format = tika.detect(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return format;
    }
}
