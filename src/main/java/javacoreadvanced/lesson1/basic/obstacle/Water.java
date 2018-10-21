package javacoreadvanced.lesson1.basic.obstacle;

import javacoreadvanced.lesson1.basic.inter.Actions;
import lombok.Getter;

/**
 * Класс водного препятствий
 * @author Mishanin Aleksey
 * @version 1.0
 */
@Getter
public class Water extends Obstacle {

    //длина водного препятствия
    private int pathLength;

    /**
     * Конструтор с параметрами
     * @param pathLength - длина водного препятствия
     * */
    public Water (int pathLength) {this.pathLength = pathLength; }

    /**
     * Реализованный метод doIt.
     * В методе просим участника проплыть заданную дистанцию
     * @param participant - участник
     * */
    @Override
    public void doIt(Actions participant){
        participant.swim(getPathLength());
    }
}
