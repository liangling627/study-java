package com.study.callback;

/**
 * @author Mack
 * @Date 2017年09月15日
 */
public class TestCallBack {
    public static void main(String[] args) {
        Ricky ricky = new Ricky();

        Teacher teacher = new Teacher(ricky);
        teacher.askQuestion();
    }

}
