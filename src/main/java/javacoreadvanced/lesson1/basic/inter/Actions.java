package javacoreadvanced.lesson1.basic.inter;

/**
 * Интерфейс объявляющий действия доступные для участников марафона
 * @author Mishanin Aleksey
 * @version 1.0
 */
public interface Actions {

    default void run (int distance) {System.out.println("Метод не реализован");}        //бег
    default void jump (double height) {System.out.println("Метод не реализован");}      //прыжок
    default void swim (int distance) {System.out.println("Метод не реализован");}       //заплыв
}
