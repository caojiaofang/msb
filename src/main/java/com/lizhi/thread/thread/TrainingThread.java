package com.lizhi.thread.thread;

import com.lizhi.thread.model.TrainingProgress;

/**
 * @Author: lizhi
 * @Description:
 * @Date: create in 2021/2/1 14:03
 */
public abstract class TrainingThread {


    protected TrainingProgress progress;

    public TrainingThread(TrainingProgress progress) {
        this.progress = progress;
        this.progress.current = 0;
        this.progress.isFinished = false;
        this.progress.isRunning = false;
    }

    public TrainingProgress getProgress() {
        return progress;
    }

    public abstract boolean start();

    public abstract void stop();

    public abstract void pause();

    public abstract void resume();
}
