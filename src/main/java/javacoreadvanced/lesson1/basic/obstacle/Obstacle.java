package javacoreadvanced.lesson1.basic.obstacle;

import javacoreadvanced.lesson1.basic.inter.Actions;

/**
 * Абстрантый класс препятствий
 * @author Mishanin Aleksey
 * @version 1.0
 */
public abstract class Obstacle {

    /**
     * Абстрактный метод описывающий попытку преодолеть препятствие
     * @param participant - участник, который предпринимает попытку пройти препятствие
     * */
    abstract public void doIt(Actions participant);
}
