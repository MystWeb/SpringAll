package com.proaimltd.poi.service;

import com.proaimltd.poi.model.dto.AuthResourceImportRespDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: ziming.xing
 * @date: 2019/8/22 10:31
 */
public interface IResourceService {
    AuthResourceImportRespDTO importPubResources(MultipartFile file);
}
