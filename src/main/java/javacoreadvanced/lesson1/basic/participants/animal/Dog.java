package javacoreadvanced.lesson1.basic.participants.animal;


/**
 * Класс Собака
 * @author Mishanin Aleksey
 * @version 1.0
 */
public class Dog extends Animal {

    public Dog(String name, int runningLength, int swimLength, double jumpHeight) {
        super(name, runningLength, swimLength, jumpHeight); //вызываем конструктор суперкласса
        setType("Пес");                                  //присваиеваем тип объекта
    }

}
