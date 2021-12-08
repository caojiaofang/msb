package com.lizhi.thread.thread;

import com.lizhi.base.context.SpringContextUtil;
import com.lizhi.thread.model.TrainingAction;
import com.lizhi.thread.model.TrainingProgress;
import com.lizhi.utils.DateUtils;
import com.lizhi.utils.NumberFormatUtils;
import com.lizhi.utils.StringUtils;
import com.lizhi.utils.VoiceUtil;
import com.lizhi.utils.cache.MemoryCacheUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @Author: lizhi
 * @Description:
 * @Date: create in 2021/2/1 14:18
 */
@Slf4j
public class ActiveTrainingThread extends TrainingThread implements Runnable {

    private final int ACTION_NONE = 0;
    private final int ACTION_PAUSE = 1; //停止
    private final int ACTION_RESUME = 2; //继续
    private final int ACTION_PLAY = 3; //首次开始

    private Thread thread = null;
    private Boolean isRunning;
    /**
     * 是否转到下一个动作,转到下一个动作之后，立即设置未false
     */
    private boolean isGoNextAction = false;
    private int nextAction = ACTION_PLAY;
    private int runedTick = 0;
    private int life = 0;


    public ActiveTrainingThread(TrainingProgress progress) {
        super(progress);
    }

    @Override
    public boolean start() {
        if (thread != null) {
            return true;
        }
        this.progress.setFinished(false);
        this.progress.setPaused(false);
        this.progress.message = "开始启动训练任务";
        this.progress.beginTime = DateUtils.getNowDate();
        thread = new Thread(this);
        this.isRunning = true;
        this.runedTick = 0;
        thread.start();
        return true;
    }

    @Override
    public void stop() {
        if (this.thread == null) {
            return;
        }
        this.isRunning = false;
    }

    @Override
    public void pause() {
        if (this.progress.isPaused()){
            return;
        }
        this.nextAction = ACTION_PAUSE;
        this.progress.setPaused(true);
    }

    @Override
    public void resume() {
        if (!this.progress.isPaused()){
            return;
        }
        this.nextAction = ACTION_RESUME;
        this.progress.setPaused(false);
        this.progress.setPauseTime(0);
    }

    @SneakyThrows
    @Override
    public void run() {

        if (!this.progress.isFinished){
            TrainingAction currentAction = null;
            while (this.isRunning){
                /*暂停*/
                if (runWithNeedPause()){
                    Thread.sleep(500);
                    continue;
                }
                /*停止训练*/
                if (runWithPause()){
                    Thread.sleep(500);
                    continue;
                }

                if (currentAction == null){
                    this.runedTick = clacRunedTick();
                    currentAction = findCurrentAction();
                    this.progress.trainingAction = currentAction;
                    log.info("开始下一个训练:{}", currentAction);
                }

                if (currentAction == null){
                    this.isRunning = false;
                    this.progress.message = "训练完毕";
                    this.progress.isFinished = true;
                    log.info("训练完毕,关闭训练,获取到的TrainingProgress:{}", this.progress);
                    break;
                } else {
                    /*运动*/
                    runWithResume(currentAction);

                    /*训练*/
                    currentAction.current = this.life / 1000;
                    currentAction.isRunning = true;

                    this.progress.current = this.runedTick + currentAction.current;

                    if (currentAction.current >= currentAction.total){
                        currentAction.current = currentAction.total;
                        currentAction.isRunning = false;
                        currentAction.isFinished = true;
                        this.life = 0;
                        currentAction = null;
                    }

                    this.life += 500;
                    Thread.sleep(500);
                }
            }
        }
        this.progress.endTime = DateUtils.getNowDate();
        log.info("训练结束");
        /*去除缓存*/
        TrainingThreadPool.remove(this.progress.getId());
    }


    /**
     * 判断暂停时间  （暂停超过2分钟自动结束训练）
     * @return
     */
    private boolean runWithPause() throws Exception {
        if (this.progress.isPaused) {
            this.progress.pauseTime += 1;
            this.progress.message = "任务暂停中";
            return true;
        }
        return false;
    }

    /**
     * 暂停 --
     * @return
     */
    private boolean runWithNeedPause() {
        if (this.nextAction == ACTION_PAUSE) {
            this.nextAction = ACTION_NONE;
            return true;
        }
        return false;
    }

    /*获取下一个动作*/
    private TrainingAction findCurrentAction() {
        for (TrainingAction action : this.progress.actions) {
            if (action.isFinished) {
                continue;
            }
            if (this.isGoNextAction) {
                this.isGoNextAction = false;
                action.isRunning = false;
                action.isFinished = true;
                action.current = action.total;
                this.runedTick += action.total;
            } else {
                this.nextAction = ACTION_PLAY;
                return action;
            }
        }
        return null;
    }

    private int clacRunedTick(){
        int runedTick = 0;
        for (TrainingAction action : this.progress.getActions()){
            if (action.isFinished){
                runedTick += action.total;
            }
        }
        return runedTick;
    }

    /**
     *功能描述 判断是否发送训练指令
     * @author lizhi
     * @date 2021/12/7
     * @param currentAction:
     * @return void
     */
    private void runWithResume(TrainingAction currentAction){
        if (this.nextAction == ACTION_PLAY) {
            this.nextAction = ACTION_NONE;
            //发送运动指令
            this.sendActionData(currentAction);
            this.progress.message = "训练中。。。。。。";
        } else if (this.nextAction == ACTION_RESUME) {
            this.nextAction = ACTION_NONE;
            //发送运动指令
            this.sendActionData(currentAction);
            this.progress.message = "训练中。。。。。。";
        }
    }

    /**
     *功能描述 发送训练动作
     * @author lizhi
     * @date 2021/12/7
     * @param action:
     * @return void
     */
    private void sendActionData(TrainingAction action){
        try {
            /*发送训练指令*/
        } catch (Exception e){
            log.info("训练出现异常结束");
            this.isRunning = false;
            this.progress.message = "训练出现异常结束";
        }
    }
}
