package javacoreprofession.lesson5.basic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static CountDownLatch LATCHSTART;
    private static AtomicBoolean BOOl;
    private static Lock lock;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        LATCHSTART = new CountDownLatch(CARS_COUNT+1);
        BOOl = new AtomicBoolean(false);
        lock = new ReentrantLock();
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            LATCHSTART.countDown();
            if(LATCHSTART.getCount()==1){
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
                LATCHSTART.countDown();
            }
            LATCHSTART.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        if(!BOOl.get()) {
            BOOl.set(true);
            System.out.println(this.name + " закончил гонку\n" + this.name + " - WIN");
        }
        else {
            System.out.println(this.name + " закончил гонку");
        }
    }

    public static int getCarsCount() {
        return CARS_COUNT;
    }

}
