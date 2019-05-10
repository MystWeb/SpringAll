package com.proaim.activiti.processDefinition;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;

import java.util.List;

/**
 * 需求：
 * 历史数据的查看
 *
 * @author ziming.xing
 * @date 2019/5/10 17:37
 */
public class HistoryQuery {
    public static void main(String[] args) {
        // 1. 创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 得到HistoryService实例
        HistoryService historyService = processEngine.getHistoryService();
        // 3. 得到HistoricActivityInstanceQuery对象
        HistoricActivityInstanceQuery query = historyService.createHistoricActivityInstanceQuery();
        query.processInstanceId("2501"); // 设置流程实例的ID
        // 4. 执行查询
        List<HistoricActivityInstance> list = query
                .orderByHistoricActivityInstanceStartTime()
                .asc().list();
        // 5. 遍历查询结果
        for (HistoricActivityInstance instance : list) {
            System.out.println(instance.getActivityId());
            System.out.println(instance.getActivityName());
            System.out.println(instance.getProcessDefinitionId());
            System.out.println(instance.getProcessInstanceId());
            System.out.println("==============================");
        }


    }
}
