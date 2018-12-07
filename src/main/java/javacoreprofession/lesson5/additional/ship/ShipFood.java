package javacoreprofession.lesson5.additional.ship;

import javacoreprofession.lesson5.additional.Itinerary;
import javacoreprofession.lesson5.additional.exception.ShipCapacityException;
import javacoreprofession.lesson5.additional.model.Goods;

/**
 * Класс описывает корабль для перевозки товара типа еда
 * @author Mishanin Aleksey
 * */
public class ShipFood extends Ship {

    public ShipFood(String name, int food, Itinerary itinerary) throws ShipCapacityException {
        super(name,0, food, 0 , Goods.FOOD, itinerary);
    }
}
