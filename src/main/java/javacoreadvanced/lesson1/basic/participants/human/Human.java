package javacoreadvanced.lesson1.basic.participants.human;

import javacoreadvanced.lesson1.basic.inter.Actions;
import javacoreadvanced.lesson1.basic.participants.Participants;

/**
 * Класс человека
 * @author Mishanin Aleksey
 * @version 1.0
 */
public class Human extends Participants implements Actions {

    public Human(String name, int runningLength, int swimLength, double jumpHeight) {
        super(name, runningLength, swimLength, jumpHeight);
        setType("Человек");
    }
}
