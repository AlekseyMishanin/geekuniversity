package javacoreprofession.lesson5.basic;

import java.util.concurrent.CountDownLatch;

public class MyThread extends Thread {

    private Car car;
    private CountDownLatch latch;

    public MyThread(CountDownLatch latch,Car car){
        this.latch=latch;
        this.car=car;
        new Thread(this);
    }

    public void run(){
        Thread th = new Thread(car);
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            latch.countDown();
        }
    }
}
