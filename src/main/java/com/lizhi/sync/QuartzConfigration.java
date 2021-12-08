package com.lizhi.sync;/**
 * @program: icrt
 * @name QuartzConfigration
 * @description:
 * @author: lizhi
 * @create: 2021-12-07 13:53
 */

import com.lizhi.task.pojo.TaskDO;
import com.lizhi.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@program: icrt
 *@name QuartzConfigration
 *@description: 定时任务配置
 *@author: lizhi
 *@create: 2021-12-07 13:53
 */
@Configuration
public class QuartzConfigration {

    @Autowired
    private TaskService taskService;

    @Bean
    public void quartzConfig(){

        TaskDO task = new TaskDO();
        /**这只要执行的类*/
        task.setBeanClass("com.lizhi.sync.jobs.NmmHeartbeatJob");
        /**设置执行的任务名称*/
        task.setJobName("nmmHeartbeatJob");
        /**设置执行的任务所在组*/
        task.setJobGroup("100");
        /**每隔30秒执行一次*/
        task.setCronExpression("0/30 * * * * ? *");
        /**启动一个定时任务*/
        taskService.initSchedule(task);
    }
}
