package javacoreprofession.lesson5.additional.ship;

import javacoreprofession.lesson5.additional.Itinerary;
import javacoreprofession.lesson5.additional.exception.ShipCapacityException;
import javacoreprofession.lesson5.additional.model.Goods;

/**
 * Класс описывает корабль для перевозки товара типа одежда
 * @author Mishanin Aleksey
 * */
public class ShipClothes extends Ship {

    public ShipClothes(String name, int clothing, Itinerary itinerary) throws ShipCapacityException {
        super(name, clothing, 0, 0, Goods.CLOTHES, itinerary);
    }
}
