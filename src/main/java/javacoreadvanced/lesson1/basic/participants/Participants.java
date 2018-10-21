package javacoreadvanced.lesson1.basic.participants;

import javacoreadvanced.lesson1.basic.inter.Actions;
import javacoreadvanced.lesson1.basic.inter.Display;
import lombok.Getter;
import lombok.Setter;

/**
 * Абстрактный класс "участник марафона". Класс определяет основные действия
 * доступные каждому участнику
 * @author Mishanin Aleksey
 * @version 1.0
 */
@Getter
@Setter
abstract public class Participants implements Actions, Display {

    protected String type;          //тип объекта
    protected String name;          //имя объекта
    protected int runningLength;    //длина пути, которую объект может пробежать
    protected int swimLength;       //длина пути, которую объект может проплыть
    protected double jumpHeight;    //высота на которую объект может подпрыгнуть
    protected boolean status;       //статус объекта. false - объект выбыл из соревнования.

    /**
     * Конструктор с параметрами
     * @param name - имя объекта
     * @param runningLength - максимальная дистанция, которую объект может пробежать
     * @param swimLength - максимльная дистанция, которую объект может проплыть
     * @param jumpHeight - максимальная высота прыжка объекта
     * */
    public Participants(final String name, final int runningLength, final int swimLength, final double jumpHeight) {
        this.name = name;
        this.runningLength = runningLength;
        this.swimLength = swimLength;
        this.jumpHeight = jumpHeight;
        this.status=true;
    }

    /**
     * Реализуем метод run интерфейса Actions.
     * */
    @Override
    public void run (final int distance){
        if (runningLength>=distance){
            System.out.println(getType() + " " + getName() + " пробежал дистанцию " + distance + " метров");
        } else{
            System.out.println(getType() + " " + getName() + " выбыл.");
            this.status=false;
        }
    }

    /**
     * Реализуем метод swim интерфейса Actions.
     * */
    @Override
    public void swim (final int distance){
        if (swimLength>=distance){
            System.out.println(getType() + " " + getName() + " проплыл дистанцию " + distance + " метров");
        } else{
            System.out.println(getType() + " " + getName() + " выбыл.");
            this.status=false;
        }
    }

    /**
     * Реализуем метод jump интерфейса Actions.
     * */
    @Override
    public void jump (final double height){
        if (jumpHeight>=height){
            System.out.println(getType() + " " + getName() +  " перепрыгнул препятствие высотой " + height + " метров");
        } else{
            System.out.println(getType() + " " + getName() + " выбыл.");
            this.status=false;
        }
    }

    /**
     * Реализуем метод display интерфейса Display.
     * */
    @Override
    public void display(){
        System.out.println(getType() + " " + getName() + " " + isStatus());
    }

}
