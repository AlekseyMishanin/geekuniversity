package javacoreprofession.lesson5.additional.port;

import javacoreprofession.lesson5.additional.model.Goods;

/**
 * Класс описывает порт, предназначенных для товара типа топливо
 * @author Mishanin Aleksey
 * */
public class PortFuel extends Port {
    public PortFuel(int fuel) {
        super(0, 0, fuel, Goods.FUEL);
    }
}
