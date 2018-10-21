package javacoreadvanced.lesson1.basic.participants.robot;

import javacoreadvanced.lesson1.basic.inter.Actions;
import javacoreadvanced.lesson1.basic.participants.Participants;

/**
 * Класс робота. Отличительная особенность класса - максимальные характеристики и единое серийное имя объекта
 * @author Mishanin Aleksey
 * @version 1.0
 */
public class Robot extends Participants implements Actions {

    public Robot() {
        super("T01", Integer.MAX_VALUE, Integer.MAX_VALUE, Double.MAX_VALUE); //вызываем конструктор суперкласса
        setType("Робот");                                                            //приваиваем тип объекта
    }

    /**
     * Реализуем метод run интерфейса Actions.
     * Рандомно при выполнении действия в объекте происходит критическая ошибка. Если критической ошибки не
     * произошло, вызываем аналогичный метод суперкласса
     * @param distance - длина дистанции
     * */
    @Override
    public void run (final int distance){
        if(Math.random()*10>3){
            super.run(distance);
        } else {
            critocalError();
        }
    }

    /**
     * Реализуем метод run интерфейса Actions.
     * Рандомно при выполнении действия в объекте происходит критическая ошибка. Если критической ошибки не
     * произошло, вызываем аналогичный метод суперкласса
     * @param distance - длина дистанции
     * */
    @Override
    public void swim (final int distance){
        if(Math.random()*10>3){
            super.swim(distance);
        } else {
            critocalError();
        }
    }

    /**
     * Реализуем метод run интерфейса Actions.
     * Рандомно при выполнении действия в объекте происходит критическая ошибка. Если критической ошибки не
     * произошло, вызываем аналогичный метод суперкласса
     * @param height - высота препятствия
     * */
    @Override
    public void jump (final double height){
        if(Math.random()*10>3){
            super.jump(height);
        } else {
            critocalError();
        }
    }

    /**
     * Метод критической ошибки. Объект ломается и выходит из строя.
     */
    private void critocalError(){
        System.out.println(getType() + " " + getName() + " перегрелся.");
        setStatus(false);
    }
}
