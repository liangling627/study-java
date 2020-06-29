package com.study.designPatterns.observer;

/**
 * @author Hank
 * @Date 2020年06月29日
 */
public abstract class Observer {
    protected  Subject subject;

    public abstract void update();
}
