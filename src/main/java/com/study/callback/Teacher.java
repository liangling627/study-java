package com.study.callback;

/**
 * @author Mack
 * @Date 2017年09月15日
 */
public class Teacher implements CallBack {

    private Student student;

    public Teacher(Student student) {
        this.student = student;
    }

    public void askQuestion() {
        student.resolveQuestion(this);
    }

    @Override
    public void tellAnswer(int answer) {
        System.out.println("我知道了你的答案：" + answer);
    }
}
