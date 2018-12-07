package javacoreprofession.lesson5.additional.port;

import javacoreprofession.lesson5.additional.model.Goods;

/**
 * Класс описывает порт, предназначенных для товара типа ела
 * @author Mishanin Aleksey
 * */
public class PortFood extends Port {
    public PortFood(int food) {
        super(0, food, 0, Goods.FOOD);
    }
}
