package com.proaimltd.poi;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.excel.util.StringUtils;
import com.proaimltd.poi.model.ExcelModel;
import com.proaimltd.poi.model.ResourceTemplate;
import com.proaimltd.poi.utils.EasyExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ziming.xing
 * @date: 2019/7/29 19:50
 */
@Slf4j
@RestController
@RequestMapping("")
@Api(tags = "Excel测试管理")
public class ExcelController {
    @Resource
    protected HttpServletResponse response;

    /**
     * 导入Excel
     */
    /*@PostMapping("/importExcel")
    public List<ExcelModel> importExcel(@RequestParam(value = "uploadFile", required = false) MultipartFile file) {
        try {
            return EasyExcelUtil.readExcelWithModel(file.getInputStream(), ExcelModel.class, ExcelTypeEnum.XLSX);
        } catch (IOException e) {
            log.error("Excel导入失败", e);
        }
        return null;
    }*/

    /**
     * 导入Excel
     */
    @PostMapping("/excel")
    @ApiOperation("导入Excel")
    public void importExcel(@RequestParam(value = "uploadFile", required = false) MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            List<Object> read = EasyExcelFactory.read(inputStream, new Sheet(1, 1, ResourceTemplate.class));
            System.out.println(read);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出Excel
     */
    @GetMapping("/writeExcel")
    @ApiOperation("导出Excel")
    public String writeExcel() {
        //初始化模拟数据
        List<ExcelModel> excelModelList = new ArrayList<>();
        for (int i = 0; i < 700; i++) {
            ExcelModel excelModel = new ExcelModel();
            excelModel.setAddress("address" + i);
            excelModel.setAge(i + "");
            excelModel.setEmail("email" + i);
            excelModel.setHeight("height" + i);
            excelModel.setLast("last" + i);
            excelModel.setName("name" + i);
            excelModel.setSax("sax" + i);
            excelModelList.add(excelModel);
        }

        return writeExcel("abc", excelModelList);
    }

    /**
     * 导出Excel工具类
     *
     * @param fileName 导出文件名，不填默认"temp.xlsx"
     * @param data     导出数据
     */
    private String writeExcel(String fileName, List<? extends BaseRowModel> data) {
        try {
            if (CollectionUtils.isEmpty(data)) {
                log.warn("Excel导出数据为空");
                return "Excel导出数据为空";
            }

            if (StringUtils.isEmpty(fileName)) {
                fileName = "temp.xlsx";
            }

            String newFileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setHeader("Content-disposition", String.format("attachment; filename=%s.xlsx", newFileName));

            // 将文件输出
            EasyExcelUtil.writeExcelWithModel(response.getOutputStream(), data, data.get(0).getClass(), ExcelTypeEnum.XLSX);
            data = null;
        } catch (Exception e) {
            log.error("Excel导出失败", e);
        }
        return "Excel导出失败";
    }

}
