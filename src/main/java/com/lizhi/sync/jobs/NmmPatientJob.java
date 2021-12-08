package com.lizhi.sync.jobs;/**
 * @program: icrt
 * @name IcrtPatientJob
 * @description:
 * @author: lizhi
 * @create: 2021-12-07 14:01
 */

import com.lizhi.base.common.Constants;
import com.lizhi.common.entity.InstancePOJO;
import com.lizhi.utils.cache.MemoryCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 *@program: icrt
 *@name IcrtPatientJob
 *@description: 患者信息上传
 *@author: lizhi
 *@create: 2021-12-07 14:01
 */
@Slf4j
@DisallowConcurrentExecution
@Component
public class NmmPatientJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        InstancePOJO instancePOJO = MemoryCacheUtil.get(Constants.System_Type);
        log.info("患者信息上传:{}", instancePOJO);
    }
}
