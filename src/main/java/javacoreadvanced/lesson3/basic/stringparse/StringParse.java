package javacoreadvanced.lesson3.basic.stringparse;

import java.util.*;

/**
 * @author Mishanin Aleksey
 * */
public class StringParse<T extends Comparable<T>> {

    //объект для хранения набора объектов
    final private Collection<T> list = new ArrayList<>();

    /**
     * Конструктор с параметрами
     * @param values - массив объектов
     * */
    public StringParse(final T ... values){
        list.addAll(Arrays.asList(values));
    }

    /**
     * Метод добевления массива объектов
     * @param values - массив объектов
     * */
    public void addAll(final T ... values){
        list.addAll(Arrays.asList(values));
    }

    /**
     * Обобщенный статический метод вывода в консоль списка уникальных значений
     * @param strings - массив объектов
     * */
    public static <E extends Comparable<E>> void printUniqueWords(final E ... strings){
        final Set<E> newStr = new HashSet<E>(Arrays.asList(strings));
        System.out.println(newStr);
    }

    /**
     * Метод вывода в консоль списка уникальных значений
     * */
    public void printUniqueWords(){
        final Set<T> newStr = new HashSet<T>(list);
        System.out.println(newStr);
    }

    /**
     * Обобщенный статический метод вывода в консоль списка пар: значение - кол-во повторений
     * @param values - массив объектов
     * */
    public static <E extends Comparable<E>> void printCountWords(final E ... values){
        final Map<E, Integer> newMap = new HashMap<>();
        for (final E value:values) {
            newMap.put(value,1 + newMap.getOrDefault(value, 0));
        }
        System.out.println(newMap);
    }

    /**
     * Метод вывода в консоль списка пар: значение - кол-во повторений
     * */
    public void printCountWords(){
        final Map<T, Integer> newMap = new HashMap<>();
        for (final T value : list) {
            newMap.put(value,1 + newMap.getOrDefault(value, 0));
        }
        System.out.println(newMap);
    }

}
