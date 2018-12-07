package javacoreprofession.lesson5.additional.port;

import javacoreprofession.lesson5.additional.model.Goods;

/**
* Класс описывает порт, предназначенных для товара типа одежда
* @author Mishanin Aleksey
* */
public class PortClothes extends Port {
    public PortClothes(int clothing) {
        super(clothing, 0, 0, Goods.CLOTHES);
    }
}
