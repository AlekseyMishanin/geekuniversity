package javacoreprofession.lesson5.additional.model;

import lombok.Getter;
import lombok.Setter;


/**
 * Класс инкапсулирует параметры корабля.
 * @author Mishanin Aleksey
 * */
@Getter
@Setter
public class ShipParemeters {

    //вместимость корабля
    private static int SIZEHOLD;
    static {
        SIZEHOLD=500;
    }
    private String name;    //имя корабля
    private int clothing;   //вместимость для типа товара одежда (<=SIZEHOLD)
    private int food;       //вместимость для типа товара еда (<=SIZEHOLD)
    private int fuel;       //вместимость для типа товара топливо (<=SIZEHOLD)
    private Goods type;     //тип товара разрешенный к перевозке на корабле
    private StatusShip status;  //статус
    private int fillingHold;    //фактическая заполненность трюма
    private int speed;          //скорость корабля

    public static int getSIZEHOLD() {
        return SIZEHOLD;
    }

    public static void setSIZEHOLD(int SIZEHOLD) {
        ShipParemeters.SIZEHOLD = SIZEHOLD;
    }
}
