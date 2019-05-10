package com.proaim.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * 查询当前用户的任务列表
 *
 * @author ziming.xing
 * @date 2019/5/9 20:29
 */
public class ActivitiTaskQuery {
    public static void main(String[] args) {
        // 1. 得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 得到TaskService对象
        TaskService taskService = processEngine.getTaskService();
        // 3. 根据流程定义的Key，负责人assignee来实现当前用户的任务列表查询
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("holiday")
                .taskAssignee("zhangsan")
                .list();
        // 4. 任务列表的展示
        for (Task task : taskList) {
            System.out.println("流程实例ID：" + task.getProcessInstanceId());
            System.out.println("任务ID：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());
        }

        // 请假单提交后，查询经理的任务列表
        /*Task managerTask = taskService.createTaskQuery()
                .processDefinitionKey("holiday")
                .taskAssignee("lisi")
                .singleResult();
        if (managerTask != null) {
            System.out.println("流程实例ID：" + managerTask.getProcessInstanceId()); // 2501
            System.out.println("任务ID：" + managerTask.getId()); // 5002
            System.out.println("任务负责人：" + managerTask.getAssignee()); // lisi
            System.out.println("任务名称：" + managerTask.getName()); // 部门经理审批
        }*/

        // 经理审批后，查询总经理的任务列表
        /*Task generalManagerTask = taskService.createTaskQuery()
                .processDefinitionKey("holiday")
                .taskAssignee("wangwu")
                .singleResult();
        if (generalManagerTask != null) {
            System.out.println("流程实例ID：" + generalManagerTask.getProcessInstanceId()); // 2501
            System.out.println("任务ID：" + generalManagerTask.getId()); // 7502
            System.out.println("任务负责人：" + generalManagerTask.getAssignee()); // wangwu
            System.out.println("任务名称：" + generalManagerTask.getName()); // 总经理审批
        }*/
    }
}
