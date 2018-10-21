package javacoreadvanced.lesson1.basic.obstacle;
import javacoreadvanced.lesson1.basic.inter.Actions;
import lombok.Getter;

/**
 * Класс препятствия для прыжка
 * @author Mishanin Aleksey
 * @version 1.0
 */
@Getter
public class Well extends Obstacle {

    //высота объекта для прыжка
    private double obstacleHeight;

    /**
     * Конструтор с параметрами
     * @param obstacleHeight - высота объекта для прыжка
     * */
    public Well(double obstacleHeight) {this.obstacleHeight=obstacleHeight; }

    /**
     * Реализованный метод doIt.
     * Метод просит участника перепрыгнуть через заданный объект
     * @param participant - участник
     * */
    @Override
    public void doIt(Actions participant){
        participant.jump(getObstacleHeight());
    }
}
