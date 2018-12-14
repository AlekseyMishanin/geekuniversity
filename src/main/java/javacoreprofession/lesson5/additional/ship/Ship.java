package javacoreprofession.lesson5.additional.ship;

import javacoreprofession.lesson5.additional.Itinerary;
import javacoreprofession.lesson5.additional.exception.ShipCapacityException;
import javacoreprofession.lesson5.additional.exception.EndVoyageException;
import javacoreprofession.lesson5.additional.model.Goods;
import javacoreprofession.lesson5.additional.model.ShipParemeters;
import javacoreprofession.lesson5.additional.model.StatusShip;

/**
 * Класс описывает основные характеристики корабля
 * @author Mishanin Aleksey
 * */
public abstract class Ship implements Runnable{

    //параметры корабля
    protected ShipParemeters paremeters;
    //набор объектов для взаимодействия с кораблем
    private Itinerary itinerary;

    public Ship(String name, int clothing, int food, int fuel, Goods type, Itinerary itinerary) throws ShipCapacityException {
        //проверяем величину заданной емкости трюма корабля на соответствие установленным требованиям (<=500)
        if((clothing+food+fuel)>ShipParemeters.getSIZEHOLD()) throw new ShipCapacityException();
        paremeters = new ShipParemeters();
        paremeters.setClothing(clothing);
        paremeters.setFood(food);
        paremeters.setFuel(fuel);
        paremeters.setType(type);
        paremeters.setName(name);
        paremeters.setStatus(StatusShip.EMPTY);
        paremeters.setSpeed(100 + (int) (Math.random() * 200));     //рандомная скорость корабля
        this.itinerary = itinerary;
    }

    /**
     * В методе формируется исключительная ситуация EndVoyageException описывающая корректное завершение работы потока(
     * портовый склад пуст, корабль прибыл в город)
     * */
    @Override
    public void run() {
        try {
            while(true) {
                for (int i = 0; i < itinerary.getPoint().size(); i++) {
                    itinerary.getPoint().get(i).go(this);
                }
            }
        } catch (EndVoyageException e){
            System.out.println(e.getMessage());
        }
    }

    public int getFillingHold() {
        return paremeters.getFillingHold();
    }
    public void setFillingHold(int value) {
        paremeters.setFillingHold(value);
    }
    /**
     * Метод описывает наполнение трюма определенным кол-вом товара
     * @param value - кол-во товара
     * */
    public void loadingIntoHold(int value) {
        paremeters.setFillingHold(getFillingHold() + value);
        paremeters.setStatus(StatusShip.FULL);
    }
    public String getName() {
        return paremeters.getName();
    }

    public Goods getType() {
        return paremeters.getType();
    }

    public int getSpeed() {
        return paremeters.getSpeed();
    }

    public StatusShip getStatus() {
        return paremeters.getStatus();
    }
    public void setStatus(StatusShip status) {
        paremeters.setStatus(status);
    }

    /**
     * Метод проверяет корабль на предмет переполнения трюма
     * @param value - кол-во единиц товара для размещения в трюме
     * */
    public boolean isFillingHold(int value){
        boolean result = false;
        switch (paremeters.getType()){
            case CLOTHES:
                result = (paremeters.getFillingHold()+value)<=paremeters.getClothing();
                break;
            case FUEL:
                result = (paremeters.getFillingHold()+value)<=paremeters.getFuel();
                break;
            case FOOD:
                result = (paremeters.getFillingHold()+value)<=paremeters.getFood();
                break;
        }
        return result;
    }
}
