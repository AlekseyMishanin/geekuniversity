package javacoreadvanced.lesson1.basic.obstacle;

import javacoreadvanced.lesson1.basic.inter.Actions;
import lombok.Getter;

/**
 * Класс препятствия для бега
 * @author Mishanin Aleksey
 * @version 1.0
 */
@Getter
public class Cross extends Obstacle{

    //дистанция для забега
    private int pathLength;

    /**
     * Конструтор с параметрами
     * @param pathLength - длина дистанции
     * */
    public Cross(int pathLength) {this.pathLength=pathLength; }

    /**
     * Реализованный метод doIt.
     * Метод просит участника пробежать дистанцию
     * @param participant - участник
     * */
    @Override
    public void doIt(Actions participant){
        participant.run(getPathLength());
    }
}
