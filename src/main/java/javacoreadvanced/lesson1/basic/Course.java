package javacoreadvanced.lesson1.basic;

import javacoreadvanced.lesson1.basic.obstacle.Obstacle;
import javacoreadvanced.lesson1.basic.participants.Participants;

/**
 * Класс "Группа препятствий"
 * @author Mishanin Aleksey
 * @version 1.0
 */
public class Course {

    final private Obstacle[] obstacle;                //массив объектов препятствий

    /**
     * Конструктор с параметрами
     * @param obstacle - массив препятствий, параметр переменной длины
     * */
    public Course(Obstacle... obstacle){
        this.obstacle=obstacle;
    }

    /**
     * Реализован метод, который просит команду пройти полосу препятствий
     * */
    public void doIt (Team team){
        for (Obstacle o: obstacle) {                                //проходим по массиву препятствий
            for (Participants participants : team.getTeam()) {      //проходим по массиву участников
                if(participants.isStatus()){                        //если статус участника true
                    o.doIt(participants);                           //просим участника пройти препятствие
                }
            }
        }
    }
}
