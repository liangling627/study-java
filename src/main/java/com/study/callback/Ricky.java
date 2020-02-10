package com.study.callback;

/**
 * @author Mack
 * @Date 2017年09月15日
 */
public class Ricky implements Student {
    @Override
    public void resolveQuestion(CallBack callBack) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }

        callBack.tellAnswer(4);
    }
}
