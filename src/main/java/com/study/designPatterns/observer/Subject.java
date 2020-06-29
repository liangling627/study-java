package com.study.designPatterns.observer;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author Hank
 * @Date 2020年06月29日
 */
public class Subject {
    private List<Observer> observers = Lists.newArrayList();

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    private void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
