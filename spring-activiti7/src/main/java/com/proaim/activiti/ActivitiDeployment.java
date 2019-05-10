package com.proaim.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * 流程定义部署
 *  操作的表：
 *   ACT_GE_BYTEARRAY
 *   ACT_RE_DEPLOYMENT
 *   ACT_RE_PROCDEF
 *
 * @author ziming.xing
 * @date 2019/5/9 16:36
 */
public class ActivitiDeployment {
    // 流程定义部署
    public static void main(String[] args) {
        // 1. 创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 得到RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3. 进行部署
        /*Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/holiday.bpmn") // 添加资源
                .addClasspathResource("diagram/holiday.png")
                .name("请假申请单流程")
                .deploy();
        // 4. 输出部署的一些信息
        System.out.println("ID：" + deployment.getId());
        System.out.println("Key：" + deployment.getKey());
        System.out.println("TenantId：" + deployment.getTenantId());
        System.out.println("Name：" + deployment.getName());
        System.out.println("Category：" + deployment.getCategory());*/

        // 3. 使用InputStream流对象获取文件
        InputStream is = ActivitiDeployment.class.getClassLoader().getResourceAsStream("diagram/holidayBPMN.zip");
        // 4. 将InputStream转换ZipInputStream流
        ZipInputStream zipInputStream = new ZipInputStream(is);
        // 5. 进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addZipInputStream(zipInputStream) // 添加资源
                .name("请假申请单流程")
                .deploy();
        // 4. 输出部署的一些信息
        System.out.println("ID：" + deployment.getId());
        System.out.println("Name：" + deployment.getName());

    }
}
