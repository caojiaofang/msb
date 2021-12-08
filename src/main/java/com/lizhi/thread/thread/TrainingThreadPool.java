package com.lizhi.thread.thread;

import com.lizhi.thread.model.TrainingProgress;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lizhi
 * @Description:
 * @Date: create in 2021/2/1 14:02
 */
@Slf4j
public class TrainingThreadPool {

    private static List<TrainingThread> threads = new ArrayList<>();

    /**
     * 开始
     * @param task
     * @return
     */
    public static boolean start(TrainingProgress task) {
        for (TrainingThread thread : threads) {
            if (thread.getProgress().id.equals(task.id)) {
                return true;
            }
        }
        TrainingThread newThread = new ActiveTrainingThread(task);
        if (newThread.start()) {
            threads.add(newThread);
            return true;
        }
        return false;
    }


    /**
     * 停止
     * @param taskId
     */
    public static void stop(String taskId) {
        for(int index = 0; index < threads.size(); index++){
            TrainingThread thread = threads.get(index);
            if(thread.getProgress().id.equals(taskId)) {
                thread.stop();
                break;
            }
        }
        remove(taskId);
    }

    /**
     * 移除该线程
     * @param taskId
     */
    public static void remove(String taskId) {
        for(int index = 0; index < threads.size(); index++){
            TrainingThread thread = threads.get(index);
            if(thread.getProgress().id.equals(taskId)) {
                threads.remove(index);
                return;
            }
        }
    }

    /**
     * 暂停
     * @param taskId
     */
    public static void pause(String taskId) {
        for(int index = 0; index < threads.size(); index++){
            TrainingThread thread = threads.get(index);
            if(thread.getProgress().id.equals(taskId)) {
                thread.pause();
                break;
            }
        }
    }

    /**
     * 继续
     * @param taskId
     */
    public static void resume(String taskId) {
        for(int index = 0; index < threads.size(); index++){
            TrainingThread thread = threads.get(index);
            if(thread.getProgress().id.equals(taskId)) {
                thread.resume();
                break;
            }
        }
    }
}
