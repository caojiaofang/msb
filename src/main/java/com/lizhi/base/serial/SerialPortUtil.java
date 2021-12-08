package com.lizhi.base.serial;/**
 * @program: hbsf
 * @name PrmSrialUtil
 * @description: 串口数据二次处理
 * @author: lizhi
 * @create: 2021-08-10 11:33
 */

import lombok.extern.slf4j.Slf4j;

/**
 *@program: hbsf
 *@name PrmSrialUtil
 *@description: 串口数据二次处理
 *@author: lizhi
 *@create: 2021-08-10 11:33
 */
@Slf4j
public class SerialPortUtil {

    /**
     * 判断接收到的指令
     * @param string
     */
    public static void RecvCommand(String string){
        log.info("接收到串口的指令是:{}", string);
    }
}
