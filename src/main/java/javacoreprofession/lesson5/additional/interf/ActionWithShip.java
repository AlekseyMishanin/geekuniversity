package javacoreprofession.lesson5.additional.interf;

import javacoreprofession.lesson5.additional.exception.EndVoyageException;
import javacoreprofession.lesson5.additional.ship.Ship;

/**
 * Интерфес описывает возможность объекта взаимодействовать с экземпляром класса производного от Ship
 * @author Mishanin Aleksey
 * */
public interface ActionWithShip {
    public void go(Ship s) throws EndVoyageException;
}
