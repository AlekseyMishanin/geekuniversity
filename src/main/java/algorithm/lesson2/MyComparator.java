package algorithm.lesson2;

import java.util.Comparator;

/**
 * Класс реализует интерфейс Comparator
 * */
public class MyComparator implements Comparator<Character> {

    @Override
    public int compare(Character o1, Character o2) {
        return o1-o2;
    }
}
