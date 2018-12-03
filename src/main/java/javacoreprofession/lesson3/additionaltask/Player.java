package javacoreprofession.lesson3.additionaltask;

import java.io.Serializable;

/**
 * Класс Player
 * @author Mishanin Aleksey
 * */
public class Player implements Serializable {

    private String name;
    private int age;

    public Player(String name, int age){
        this.name=name;
        this.age=age;
    }

    public void info(){
        System.out.println("name - " + name + ", age - " + age);
    }
}
