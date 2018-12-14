package javacoreprofession.lesson5.additional.ship;

import javacoreprofession.lesson5.additional.Itinerary;
import javacoreprofession.lesson5.additional.exception.ShipCapacityException;
import javacoreprofession.lesson5.additional.model.Goods;

/**
 * Класс описывает корабль для перевозки товара любого набора(не реализовано)
 * @author Mishanin Aleksey
 * */
public class ShipCustomized extends Ship{

    public ShipCustomized(String name, int clothing, int food, int fuel, Itinerary itinerary) throws ShipCapacityException {
        super(name, clothing, food, fuel, Goods.ALL, itinerary);
    }
}
