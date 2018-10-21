package javacoreadvanced.lesson1.basic.participants.animal;
import javacoreadvanced.lesson1.basic.participants.Participants;

/**
 * Абстрактный класс животного
 * @author Mishanin Aleksey
 * @version 1.0
 */
public abstract class Animal extends Participants {


    public Animal(String name, int runningLength, int swimLength, double jumpHeight) {
        super(name, runningLength, swimLength, jumpHeight);         //вызываем конструктор суперкласса
    }
}
