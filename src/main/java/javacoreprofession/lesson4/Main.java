package javacoreprofession.lesson4;

/*
* 1. Создать три потока, каждый из которых выводит определенную букву
* (A, B и C) 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
* */

public class Main {

    private final Object obj = new Object();
    private final int size = 5;
    private volatile int aByte = 0b01000001;

    public static void main(String[] args) {
        Main main = new Main();
        new Thread(()->main.printA()).start();
        new Thread(()->main.printB()).start();
        new Thread(()->main.printC()).start();
    }

    public void printA(){
        synchronized (this){
            try{
                for (int i = 0; i < size; i++) {
                    while(aByte!=0b01000001){
                        wait();
                    }
                    System.out.print((char)aByte);
                    Thread.sleep(500);
                    aByte=aByte+1;
                    notifyAll();
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void printB(){
        synchronized (this){
            try{
                for (int i = 0; i < size; i++) {
                    while(aByte!=0b01000010){
                        wait();
                    }
                    System.out.print((char)aByte);
                    Thread.sleep(500);
                    aByte=aByte+1;
                    notifyAll();
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void printC(){
        synchronized (this){
            try{
                for (int i = 0; i < size; i++) {
                    while(aByte!=0b01000011){
                        wait();
                    }
                    System.out.print((char)aByte);
                    Thread.sleep(500);
                    aByte=aByte-2;
                    notifyAll();
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
