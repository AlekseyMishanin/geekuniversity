package javacoreadvanced.lesson1.basic;

import javacoreadvanced.lesson1.basic.participants.Participants;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс Команда
 * @author Mishanin Aleksey
 * @version 1.0
 */
@Getter
public class Team {

    final private String name;                    //наименование команды
    final private ArrayList<Participants> team;   //массив членов команды

    /**
     * Конструктор с параметрами
     * @param name - наименование команды
     * @param participants - массив членов команды
     * */
    public Team(String name, Participants... participants){
        this.name = name;                                 //присваиваем имя команды
        this.team = new ArrayList<Participants>();      //создаем объект ArrayList
        this.team.addAll(Arrays.asList(participants));  //добавляем в объект список участников
    }

    /**
     * Выводим список всех участников
     * */
    public void showTeam(){
        for (Participants participants: team) {
            participants.display();
        }
    }

    /**
     * Выводим список победителей
     * */
    public void showWinners(){
        for (Participants participants: team) {
            if(participants.isStatus()) {
                participants.display();
            }
        }
    }
}
