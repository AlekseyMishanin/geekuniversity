package javacoreprofession.lesson1.vegetablebase.inter;

import javacoreprofession.lesson1.vegetablebase.box.Box;
import javacoreprofession.lesson1.vegetablebase.fruit.Fruit;

/**
 * Интерфейс BoxActions.
 * Интерфейс описывает действия, которые можно выполнять с коробкой
 * @author - Mishanin Aleksey
 * */
public interface BoxActions<T extends Fruit>{

    boolean add (T value);              //добавить одну штуку в коробку
    boolean pour (Box<T> value);        //добавить n-e количество штук в коробку
}
