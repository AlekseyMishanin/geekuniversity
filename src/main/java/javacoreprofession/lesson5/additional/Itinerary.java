package javacoreprofession.lesson5.additional;

import javacoreprofession.lesson5.additional.interf.ActionWithShip;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс описывает набор объектов для взаимодействия с кораблем
 * */
public class Itinerary {
    private ArrayList<ActionWithShip> points;
    public ArrayList<ActionWithShip> getPoint() { return points; }
    public Itinerary(ActionWithShip... points) {
        this.points = new ArrayList<>(Arrays.asList(points));
    }
}
