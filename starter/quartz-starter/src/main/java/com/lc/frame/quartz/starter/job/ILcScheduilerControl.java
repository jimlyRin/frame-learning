package com.lc.frame.quartz.starter.job;

/**
 * ILcScheduilerControl
 *
 * @author jimlyRin
 */
public interface ILcScheduilerControl {

    void start();

    void shutdown();

    void pause();

    void resume();
}
