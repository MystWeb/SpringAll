/*
 * © Copyright Process Asset Integration and Management Limited 2013 - 2019.
 *  All rights reserved.
 */

package com.proaimltd.poi.model.dto;

/**
 * @project: trinity-parent
 * @packageName: com.proaimltd.web.common.entity.security.resource.dto
 * @author: Administrator
 * @date: 2019/7/26 14:57
 * @description：TODO
 */

import com.proaimltd.poi.model.ResourceTemplate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: ziming.xing
 * @date: 2019/7/30 10:57
 */
@Data
@ApiModel(description = "Excel批量导入资源返回")
public class AuthResourceImportRespDTO implements Serializable {

    private static final long serialVersionUID = 2197119848256477671L;

    @ApiModelProperty(value = "导入的数据")
    private List<ResourceTemplate> resourceTemplates;

    @ApiModelProperty("上传的文件名称")
    private String fileName;

    @ApiModelProperty(value = "返回行数")
    private AtomicInteger result;
}

