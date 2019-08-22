package com.proaimltd.poi.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ziming.xing
 * @date: 2019/7/29 17:03
 */
public class ExcelListener extends AnalysisEventListener {

    private List<Object> data = new ArrayList<Object>();

    @Override
    public void invoke(Object object, AnalysisContext context) {
        System.out.println(context.getCurrentSheet());
        data.add(object);
        if (data.size() >= 100) {
            doSomething();
            data = new ArrayList<Object>();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        doSomething();
    }

    private void doSomething() {
        for (Object o : data) {
            System.out.println(o);
        }
    }
}
