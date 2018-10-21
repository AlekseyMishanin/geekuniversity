package javacoreadvanced.lesson1.basic.participants.animal;


/**
 * Класс Кот
 * @author Mishanin Aleksey
 * @version 1.0
 */
public class Cat extends Animal {

    public Cat(String name, int runningLength, int swimLength, double jumpHeight) {
        super(name, runningLength, swimLength, jumpHeight);         //вызываем конструктор суперкласса
        setType("Кот");                                          //присваиваем тип объекта
    }

}
