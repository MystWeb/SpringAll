package com.proaimltd.poi;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.proaimltd.poi.model.AuthResource;
import com.proaimltd.poi.model.ResourceTemplate;
import com.proaimltd.poi.model.dto.AuthResourceImportRespDTO;
import com.proaimltd.poi.service.IResourceService;
import com.proaimltd.poi.utils.EasyExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ziming.xing
 * @date: 2019/8/22 10:18
 */
@RestController
@Api(tags = "资源管理")
@RequestMapping("/resources")
public class ResourceController {
    private final IResourceService resourceService;

    @Resource
    private HttpServletResponse response;

    public ResourceController(IResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * 导入Excel
     */
    @PostMapping("/excel")
    @ApiOperation("Excel导入资源")
    public AuthResourceImportRespDTO importExcel(@RequestParam(value = "uploadFile", required = false) MultipartFile file) {
        return resourceService.importPubResources(file);
    }

    @GetMapping("/exportExcel")
    @ApiOperation("Excel导出资源")
    public void exportExcel(/*@RequestParam String parameter*/) {
        /*List<AuthResource> resources = resourceService.findPubResources(null);*/
        List<AuthResource> resources = new ArrayList<>();
        resources.add(new AuthResource());
        // Java List复制
        List<ResourceTemplate> resourceTemplates = new ArrayList<>();
        for (AuthResource resource : resources) {
            ResourceTemplate resourceTemplate = new ResourceTemplate();
            BeanUtils.copyProperties(resource, resourceTemplate);
            resourceTemplates.add(resourceTemplate);
        }
        try {
            EasyExcelUtil.writeExcel("Resource", ExcelTypeEnum.XLSX, resourceTemplates, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
