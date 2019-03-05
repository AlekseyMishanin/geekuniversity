package algorithm.lesson3;

/*
1. Реализовать рассмотренные структуры данных в консольных программах.
2. Создать программу, которая переворачивает вводимые строки (читает справа налево).
3. Создать класс для реализации дека.
*/

public class Main {

    public static void main(String[] args) {

        MyDequeArr<Character> deq = new MyDequeArr<>(Character.class);
        deq.insertRight('a');
        deq.insertRight('b');
        deq.insertRight('c');
        deq.insertLeft('d');
        deq.insertLeft('p');
        System.out.println(deq);
        deq.removeLeft();
        System.out.println(deq);
        deq.removeRight();
        System.out.println(deq);
    }
}
