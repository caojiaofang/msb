package com.lizhi.sync.jobs;/**
 * @program: icrt
 * @name IcrtHeartbeatJob
 * @description: 心跳
 * @author: lizhi
 * @create: 2021-12-07 13:56
 */

import com.lizhi.common.common.Constants;
import com.lizhi.common.entity.InstancePOJO;
import com.lizhi.common.entity.RpcClientPOJO;
import com.lizhi.common.entity.RpcServicePOJO;
import com.lizhi.common.enums.HeartBeartEnum;
import com.lizhi.db.upp.dao.UppOrganDao;
import com.lizhi.db.upp.pojo.UppOrganPOJO;
import com.lizhi.utils.DateUtils;
import com.lizhi.utils.StringUtils;
import com.lizhi.utils.cache.MemoryCacheUtil;
import com.lizhi.utils.httprequest.HttpRequestUtils;
import com.lizhi.utils.json.SamJsonUtil;
import com.lizhi.utils.tools.BeanCopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *@program: icrt
 *@name IcrtHeartbeatJob
 *@description: 心跳
 *@author: lizhi
 *@create: 2021-12-07 13:56
 */
@Slf4j
@DisallowConcurrentExecution
@Component
public class NmmHeartbeatJob implements Job {

    @Resource
    private UppOrganDao uppOrganDao;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<UppOrganPOJO> uppOrganPOJOS = uppOrganDao.selectByMap(null, null);
        if (uppOrganPOJOS.size() > 0){
            UppOrganPOJO uppOrganPOJO = uppOrganPOJOS.get(0);
            RpcClientPOJO rpcClientPOJO = new RpcClientPOJO();
            BeanCopyUtil.copy(uppOrganPOJO, rpcClientPOJO);
            /*设置系统类别*/
            rpcClientPOJO.setSystemType(com.lizhi.base.common.Constants.System_Type);
            rpcClientPOJO.setHeartTm(DateUtils.getNowDate());
            String json = SamJsonUtil.toJson(rpcClientPOJO);

            String url = uppOrganPOJO.getParentOrganId() + Constants.HEART_BEAT_URL;
            String string = HttpRequestUtils.httpPost(url, json, Constants.CONTENT_TYPE_JSON);
            log.info("获取到的响应信息string:{}", string);
            if (!StringUtils.isEmpty(string)){
                RpcServicePOJO rpcServicePOJO = SamJsonUtil.toObject(string, RpcServicePOJO.class);
                if (HeartBeartEnum.HEART_BEART_000000.getEnValue().equals(rpcServicePOJO.getErrorCode())){
                    List<InstancePOJO> instancePOJOS = rpcServicePOJO.getInstancePOJOS();
                    List<InstancePOJO> list = new ArrayList<>();
                    for (InstancePOJO instancePOJO : instancePOJOS){
                        if ("UP".equals(instancePOJO.getState().toUpperCase())){
                            instancePOJO.setHospitalId(rpcClientPOJO.getOrganId());
                            instancePOJO.setHospitalSystemId(rpcClientPOJO.getBkOrganId());
                            list.add(instancePOJO);
                        }
                    }
                    if (list.size() > 0){
                        /*随机算法*/
                        Random random = new Random();
                        int i = random.nextInt(list.size());
                        MemoryCacheUtil.put(com.lizhi.base.common.Constants.System_Type, list.get(i), 90000);
                    }
                }
            }
        }
    }
}
