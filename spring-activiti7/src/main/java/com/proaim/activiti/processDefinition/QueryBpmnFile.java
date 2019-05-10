package com.proaim.activiti.processDefinition;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 需求：
 * 1. 从Activiti的ACT_GE_BYTEARRAY表中读取两个资源文件
 * 2. 将两个资源文件保存到路径：D:\\IdeaProjects\\activiti7\\src\\main\\resources\\upload
 * <p>
 * 技术方案：
 * 1. 第一种方式：使用Activiti的api实现
 * 2. 第二种方式：其实就是原理层面，可以使用JDBC对blob类型，或者clob类型数据的读取并保存。
 * 3. IO流转换，最好commons-io.jar包可以轻松解决IO操作
 * 真实应用场景：用户想查看这个请假流程具体有哪些步骤要走？
 *
 * @author ziming.xing
 * @date 2019/5/10 15:20
 */
public class QueryBpmnFile {
    public static void main(String[] args) {
        // 1. 创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 得到RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3. 得到查询器：ProcessDefinitionQuery对象
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        // 4. 设置查询条件 参数：流程定义的Key
        processDefinitionQuery.processDefinitionKey("holiday");
        // 5. 执行查询操作，查询出想要的流程定义
        ProcessDefinition processDefinition = processDefinitionQuery.singleResult();
        // 6. 通过流程定义信息，得到部署ID、bpmn的文件Name、图片资源Name
        String deploymentId = processDefinition.getDeploymentId();
        // getDiagramResourceName()方法说明：获取png图片的资源名称
        String diagramResourceName = processDefinition.getDiagramResourceName();
        // getDiagramResourceName()方法说明：获取bpmn的文件名称
        String resourceName = processDefinition.getResourceName();
        try {
            // 7. 通过repositoryService的方法，实现读取图片信息及bpmn文件信息（输入流InputStream）
            // repositoryService.getResourceAsStream()方法参数说明：第一个参数部署id，第二个参数代表资源名称
            InputStream pngIs = repositoryService.getResourceAsStream(deploymentId, diagramResourceName);
            InputStream bpmnIs = repositoryService.getResourceAsStream(deploymentId, resourceName);
            // 8. 构建出输出流OutputStream流
            OutputStream pngOs = new FileOutputStream("D:\\IdeaProjects\\activiti7\\src\\main\\resources\\upload\\" + diagramResourceName);
            OutputStream bpmnOs = new FileOutputStream("D:\\IdeaProjects\\activiti7\\src\\main\\resources\\upload\\" + resourceName);
            // 9. 输入流、输出流的转换 commons-io-xx.jar中的方法
            IOUtils.copy(pngIs, pngOs);
            IOUtils.copy(bpmnIs, bpmnOs);
            // 10. 关闭流
            pngOs.close();
            bpmnIs.close();
            pngIs.close();
            pngIs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
