package com.proaimltd.poi;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.proaimltd.poi.model.ExcelModel;
import com.proaimltd.poi.utils.EasyExcelUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ziming.xing
 * @date: 2019/7/29 19:37
 */
public class EasyExcelTest {
    public static void main(String[] args) throws Exception {
        writeExcel();
        readExcel();
    }

    /**
     * 写入Excel
     *
     * @throws FileNotFoundException
     */
    private static void writeExcel() throws IOException {
        List<ExcelModel> excelModelList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            ExcelModel excelModel = new ExcelModel();
            excelModel.setAddress("address" + i);
            excelModel.setAge(i + "");
            excelModel.setEmail("email" + i);
            excelModel.setHeight("heigh" + i);
            excelModel.setLast("last" + i);
            excelModel.setName("name" + i);
            excelModel.setSax("sax" + i);
            excelModelList.add(excelModel);
        }

        long beginTime = System.currentTimeMillis();
        OutputStream outputStream = new FileOutputStream("D:/aaa.xlsx");
        EasyExcelUtil.writeExcelWithModel(outputStream, excelModelList, ExcelModel.class, ExcelTypeEnum.XLSX);
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("总共耗时 %s 毫秒", (endTime - beginTime)));
        outputStream.close();
        excelModelList = null;
    }

    /**
     * 读取Excel
     *
     * @throws FileNotFoundException
     */
    private static void readExcel() throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("D:/aaa.xlsx");
            //读入文件，每一行对应一个 Model，获取 Model 列表
            List<ExcelModel> objectList = EasyExcelUtil.readExcelWithModel(inputStream, ExcelModel.class, ExcelTypeEnum.XLSX);
            for (ExcelModel excelModel : objectList) {
                System.out.println(excelModel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert inputStream != null;
            inputStream.close();
        }
    }

}