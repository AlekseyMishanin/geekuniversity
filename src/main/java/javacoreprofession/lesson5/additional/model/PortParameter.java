package javacoreprofession.lesson5.additional.model;

import javacoreprofession.lesson5.additional.exception.StockroomEmptyException;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс инкапсулирует параметры порта.
 * @author Mishanin Aleksey
 * */
public class PortParameter {

    //вместимость портового крана, т.е. кол-во единиц загружаемых на корабль за одну итерацию
    private static int CAPACITYCRANE;
    static {
        CAPACITYCRANE=100;
    }

    private AtomicInteger clothing; //размер склада для товара типа одежда
    private AtomicInteger food;     //размер склада для товара типа еда
    private AtomicInteger fuel;     //размер склада для товара типа топливо
    private int distance;           //расстояние от пролива до порта
    private Goods type;             //тип товара размещаемого в порту
    private StatusPort status;      //статус порта

    public PortParameter() {
        this.clothing = new AtomicInteger();
        this.food = new AtomicInteger();
        this.fuel = new AtomicInteger();
    }

    public static int getCAPACITYCRANE() {
        return CAPACITYCRANE;
    }

    public static void setCAPACITYCRANE(int CAPACITYCRANE) {
        PortParameter.CAPACITYCRANE = CAPACITYCRANE;
    }

    public void setClothing(int clothing) {
        this.clothing.set(clothing);
    }

    public void setFood(int food) {
        this.food.set(food);
    }

    public void setFuel(int fuel) {
        this.fuel.set(fuel);
    }

    /**
     * Метод отгрузки со склада товара типа одежда.
     * @param clothing - кол-во единиц товара забираемого со склада
     * @exception StockroomEmptyException - генерируется если склад пуст
     * */
    public void loadingClothing(int clothing) throws StockroomEmptyException {
        if (clothing<0) {
            throw new StockroomEmptyException();
        } else {
            this.clothing.set(clothing);
        }
    }

    /**
     * Метод отгрузки со склада товара типа еда.
     * @param food - кол-во единиц товара забираемого со склада
     * @exception StockroomEmptyException - генерируется если склад пуст
     * */
    public void loadingFood(int food) throws StockroomEmptyException{
        if (food<0) {
            throw new StockroomEmptyException();
        } else {
            this.food.set(food);
        }
    }

    /**
     * Метод отгрузки со склада товара типа топливо.
     * @param fuel - кол-во единиц товара забираемого со склада
     * @exception StockroomEmptyException - генерируется если склад пуст
     * */
    public void loadingFuel(int fuel) throws StockroomEmptyException{
        if (fuel<0) {
            throw new StockroomEmptyException();
        } else {
            this.fuel.set(fuel);
        }
    }

    public int getClothing() {
        return clothing.get();
    }

    public int getFood() {
        return food.get();
    }

    public int getFuel() {
        return fuel.get();
    }

    public Goods getType() {
        return type;
    }

    public void setType(Goods type) {
        this.type = type;
    }

    public StatusPort getStatus() {
        return status;
    }

    public void setStatus(StatusPort status) {
        this.status = status;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
