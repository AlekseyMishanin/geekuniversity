package javacoreprofession.lesson5.additional.exception;

/**
 * Класс исключительной ситуации, которая появляется при условии создания экземпляра класса производного от Ship с
 * параметром вместимости трюма превышающим допустимое значение
 * @author Mishanin Aleksey
 * */
public class ShipCapacityException extends Exception {
    public ShipCapacityException(){
        super("Некорректное значение допустимой вместимости корабля.");
    }
}
