package javacoreprofession.lesson5.additional.exception;

/**
 * Класс исключительной ситуации, которая появляется при условии что склад поставщика товара пуст
 * @author Mishanin Aleksey
 * */
public class StockroomEmptyException extends Exception {
    public StockroomEmptyException(){
        super("Склад пуст.");
    }
}
