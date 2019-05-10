package com.proaim.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * 启动流程实例：
 *  已经完成流程定义的部署工作
 * 影响的表：
 *  ACT_HI_ACTINST 已完成的活动信息
 *  ACT_HI_IDENTITYLINK 参与者信息
 *  ACT_HI_PROCINST 流程实例
 *  ACT_HI_TASKINST 任务实例
 *  ACT_RU_EXECUTION 执行表
 *  ACT_RU_IDENTITYLINK 参与者信息
 *  ACT_RU_TASK 任务
 * @author ziming.xing
 * @date 2019/5/9 20:01
 */
public class ActivitiStartInstance {
    public static void main(String[] args) {
        // 1. 得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 得到RuntimeService对象
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 3. 创建实例对象 ACT_RE_PROCDEF：holiday
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday");
        // 4. 输出实例的相关信息
        System.out.println("流程部署ID：" + processInstance.getDeploymentId());
        System.out.println("流程实例ID：" + processInstance.getId());
        System.out.println("活动ID：" + processInstance.getActivityId());
    }
}
