package javacoreprofession.lesson5.additional.exception;

import java.util.Random;

/**
 * Класс исключительной ситуации, которая появляется по прибытии корабля в город при условии, что
 * портовый склад поставщика пуст
 * @author - Mishanin Aleksey
 * */
public class EndVoyageException extends Exception {

    private String[] strArr = {
            "Матросы разбрелась по кабакам...",
            "Матросы разбрелась по барделям...",
            "Матросы загремели за решетку...",
            "Матросы разбрелись по семьям..."};
    private Random random;

    public EndVoyageException(){super(); random = new Random();}

    @Override
    public String getMessage() {
        return "Плавание завершилось....\n" + strArr[random.nextInt(3)];
    }
}
