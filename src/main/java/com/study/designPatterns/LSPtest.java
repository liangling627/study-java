package com.study.designPatterns;

/**
 * @author Hank
 * @Date 2019年05月22日
 */
public class LSPtest {

    public static void main(String[] args) {
        Bird bird = new Swallow();
        Bird bird1 = new BrownKiwi();
        bird.setSpeed(120);
        bird1.setSpeed(120);
        System.out.println("如果飞行300公里：");

        try {
            System.out.println("燕子将飞行：" + bird.getFlyTime(300) + "小时");
            System.out.println("几维鸟将飞行：" + bird1.getFlyTime(300) + "小时");
        } catch (Exception e) {
            System.out.println("发生错误了!");
        }

    }


}

class Bird {
    double flyspeed;

    public void setSpeed(double flyspeed) {
        this.flyspeed = flyspeed;
    }

    public double getFlyTime(double distence) {
        return (distence/flyspeed);
    }
}

class Swallow extends Bird {

}

class BrownKiwi extends Bird {

    @Override
    public void setSpeed(double speed) {
        flyspeed = 0;
    }
}
