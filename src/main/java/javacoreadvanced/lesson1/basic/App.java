package javacoreadvanced.lesson1.basic;

/*
1. Разобраться с имеющимся кодом;

2. Добавить класс Team, который будет содержать название команды,
массив из четырех участников (в конструкторе можно сразу указыватьвсех
участников ), метод для вывода информации о членах команды,
прошедших дистанцию, метод вывода информации обо всех членах команды.

3. Добавить класс Course (полоса препятствий), в котором будут находиться
массив препятствий и метод, который будет просить команду пройти всю полосу;
В итоге должно быть что-то вроде:

public static void main(String[] args) {
Course c = new Course(...); // Создаем полосу препятствий
Team team = new Team(...); // Создаем команду
c.doIt(team); // Просим команду пройти полосу
team.showResults(); // Показываем результаты
}
*/

import javacoreadvanced.lesson1.basic.participants.human.Human;
import javacoreadvanced.lesson1.basic.participants.robot.Robot;
import javacoreadvanced.lesson1.basic.participants.animal.Cat;
import javacoreadvanced.lesson1.basic.participants.animal.Dog;
import javacoreadvanced.lesson1.basic.obstacle.Cross;
import javacoreadvanced.lesson1.basic.obstacle.Water;
import javacoreadvanced.lesson1.basic.obstacle.Well;

public class App {

    public static void main(String[] args) {

        final Course course = new Course(new Cross(850), new Water(500), new Well(2.5));
        final Team team = new Team("Спартанцы",
                new Robot(),
                new Human("Пипко В.В.", 900, 500, 2.6),
                new Cat("Мурзик",1000,0,3.0),
                new Dog("Бим", 2000, 1000, 2.7));

        System.out.println("Команда " + team.getName() + ":");
        team.showTeam();

        System.out.println();
        course.doIt(team);

        System.out.println();
        team.showWinners();
    }
}
