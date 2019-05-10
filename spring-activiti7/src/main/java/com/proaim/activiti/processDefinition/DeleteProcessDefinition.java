package com.proaim.activiti.processDefinition;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;

import java.util.List;

/**
 * 删除流程定义信息
 * 注意事项：
 *  1. 当我们正在执行的这一套流程没有完全审批结束的时候，流程定义信息会删除失败
 *  2. 如果公司层面要强制删除，可以使用repositoryService.deleteDeployment("1",true);
 *  // 参数true代表级联删除，此时就会先删除没有完成的流程节点，最后就可以删除流程定义信息。
 *  操作的表：
 *      ACT_GE_BYTEARRAY
 *      ACT_RE_DEPLOYMENT
 *      ACT_RE_PROCDEF
 * @author ziming.xing
 * @date 2019/5/10 14:03
 */
public class DeleteProcessDefinition {
    public static void main(String[] args) {
        // 1. 得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 创建RepositoryService对象
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3. 执行删除流程定义
        repositoryService.deleteDeployment("1");

    }
}
