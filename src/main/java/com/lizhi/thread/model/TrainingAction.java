package com.lizhi.thread.model;/**
 * @program: hbsf
 * @name AssessAction
 * @description: 评估动作集合
 * @author: lizhi
 * @create: 2021-08-10 15:53
 */

import com.lizhi.mybatis.boot.Description;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *@program: hbsf
 *@name AssessAction
 *@description: 评估动作集合
 *@author: lizhi
 *@create: 2021-08-10 15:53
 */
@Getter
@Setter
@ToString
public class TrainingAction {

    /*是否结束*/
    public boolean isFinished;

    public boolean isRunning;

    @Description("单次总时间")
    public Integer total;

    @Description("当前时间")
    public Integer current;

}
