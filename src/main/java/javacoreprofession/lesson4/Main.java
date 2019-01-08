package javacoreprofession.lesson4;

/*
* 1. Создать три потока, каждый из которых выводит определенную букву
* (A, B и C) 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
* */

public class Main {

    private final Object obj = new Object();
    private final int size = 5;
    private volatile char ch = 'A';

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
                    while(ch!='A'){
                        wait();
                    }
                    System.out.print(ch);
                    Thread.sleep(500);
                    ch='B';
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
                    while(ch!='B'){
                        wait();
                    }
                    System.out.print(ch);
                    Thread.sleep(500);
                    ch='C';
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
                    while(ch!='C'){
                        wait();
                    }
                    System.out.print(ch);
                    Thread.sleep(500);
                    ch='A';
                    notifyAll();
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
