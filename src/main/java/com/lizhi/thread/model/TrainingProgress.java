package com.lizhi.thread.model;

import com.lizhi.mybatis.boot.Description;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author: lizhi
 * @Description:
 * @Date: create in 2020/6/16 13:47
 */
@Setter
@Getter
@ToString
public class TrainingProgress {

    @Description("taskid")
    public String id;

    @Description("训练总时间")
    public Integer total;

    @Description("训练当前时间")
    public Integer current;

    @Description("训练开始时间")
    public String beginTime;

    @Description("训练结束时间")
    public String endTime;

    @Description("是否暂停")
    public boolean isPaused;

    @Description("暂停的时间")
    public long pauseTime = 0;

    @Description("是否完成")
    public boolean isFinished;

    @Description("是否还在运行")
    public boolean isRunning;

    public String message;

    @Description("声音")
    public String voice;

    @Description("当前动作")
    public TrainingAction trainingAction;

    @Description("动作集合")
    public List<TrainingAction> actions;


}
