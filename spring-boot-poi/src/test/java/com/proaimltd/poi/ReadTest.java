package com.proaimltd.poi;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.proaimltd.poi.listener.ExcelListener;
import com.proaimltd.poi.model.ReadModel;
import com.proaimltd.poi.model.ReadModel2;
import com.proaimltd.poi.utils.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadTest {

    /**
     * 07版本excel读数据量少于1千行数据，内部采用回调方法.
     *
     * @throws IOException 简单抛出异常，真实环境需要catch异常,同时在finally中关闭流
     */
    @Test
    public void simpleReadListStringV2007() throws IOException {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("1.xls");
        /**
         * sheetNo：Excel左下角页面（下标 1 开始）
         * headLineMun：Excel的标题行数（下标 0 开始）
         * 姓名	年龄  性别
         * temp1  1    男
         * temp2  2    男
         */
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 1));
        inputStream.close();
        System.out.println(data);
    }

    /**
     * 07版本excel读数据量少于1千行数据自动转成javamodel，内部采用回调方法.
     *
     * @throws IOException 简单抛出异常，真实环境需要catch异常,同时在finally中关闭流
     */
    @Test
    public void simpleReadJavaModelV2007() throws IOException {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("2007.xls");
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(2, 2, ReadModel.class));
        inputStream.close();
        System.out.println(data);
    }

    /**
     * 07版本excel读数据量大于1千行，内部采用回调方法.
     *
     * @throws IOException 简单抛出异常，真实环境需要catch异常,同时在finally中关闭流
     */
    @Test
    public void saxReadListStringV2007() throws IOException {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("2007.xlsx");
        ExcelListener excelListener = new ExcelListener();
        EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1), excelListener);
        inputStream.close();

    }

    /**
     * 07版本excel读取sheet
     *
     * @throws IOException 简单抛出异常，真实环境需要catch异常,同时在finally中关闭流
     */
    @Test
    public void saxReadSheetsV2007() throws IOException {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("2007.xlsx");
        ExcelListener excelListener = new ExcelListener();
        ExcelReader excelReader = EasyExcelFactory.getReader(inputStream, excelListener);
        List<Sheet> sheets = excelReader.getSheets();
        System.out.println("sheets：****" + sheets);
        System.out.println();
        for (Sheet sheet : sheets) {
            if (sheet.getSheetNo() == 1) {
                excelReader.read(sheet);
            } else if (sheet.getSheetNo() == 2) {
                sheet.setHeadLineMun(1);
                sheet.setClazz(ReadModel.class);
                excelReader.read(sheet);
            } else if (sheet.getSheetNo() == 3) {
                sheet.setHeadLineMun(1);
                sheet.setClazz(ReadModel2.class);
                excelReader.read(sheet);
            }
        }
        inputStream.close();
    }

    private void print(List<Object> data) {
        int i = 0;
        for (Object ob : data) {
            System.out.println(i++);
            System.out.println(ob);
        }
    }
}
