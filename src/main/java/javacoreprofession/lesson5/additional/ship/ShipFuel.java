package javacoreprofession.lesson5.additional.ship;

import javacoreprofession.lesson5.additional.Itinerary;
import javacoreprofession.lesson5.additional.exception.ShipCapacityException;
import javacoreprofession.lesson5.additional.model.Goods;

/**
 * Класс описывает корабль для перевозки товара типа топливо
 * @author Mishanin Aleksey
 * */
public class ShipFuel extends Ship {

    public ShipFuel(String name, int fuel, Itinerary itinerary) throws ShipCapacityException {
        super(name,0, 0, fuel, Goods.FUEL, itinerary);
    }
}
