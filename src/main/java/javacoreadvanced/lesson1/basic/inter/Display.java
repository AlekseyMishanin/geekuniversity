package javacoreadvanced.lesson1.basic.inter;

/**
 * Интерфейс объявляет метод для вывода информации об объекте
 * @author Mishanin Aleksey
 * @version 1.0
 */
public interface Display {

    default void display() {System.out.println("Метод не реализован");}
}
