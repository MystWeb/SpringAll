package com.proaim.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * 处理当前用户的任务
 * 操作的表：
 * ACT_HI_ACTINST：
 * ACT_HI_IDENTITYLINK
 * ACT_HI_TASKINST
 * ACT_RU_INTEGRATION
 * ACT_RU_TASK
 *
 * @author ziming.xing
 * @date 2019/5/9 20:29
 */
public class ActivitiCompleteTask {
    public static void main(String[] args) {
        // 1. 得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 得到TaskService对象
        TaskService taskService = processEngine.getTaskService();

        // 完成zhangsan的任务
        // 3. 处理任务，结合当前用户任务列表的查询操作的话，任务ID：2505
        /*taskService.complete("2505");*/

        // 完成lisi的任务
        // 3. 处理任务，结合当前用户任务列表的查询操作的话，任务ID：5002
        /*taskService.complete("5002");*/

        // 完成wangwu的任务
        // 3. 处理任务，结合当前用户任务列表的查询操作的话，任务ID：5002
        taskService.complete("7502");

        // 查询当前用户的任务并处理掉
        // 3. 查询当前用户wangwu的任务
        /*List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("holiday")
                .taskAssignee("wangwu")
                .list();*/
        // 4. 处理任务，结合当前用户任务列表的查询操作，任务ID：task.getId()
        /*for (Task task : taskList) {
            taskService.complete(task.getId());
        }*/
    }
}
