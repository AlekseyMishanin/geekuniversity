package javacoreprofession.lesson1;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Mishanin Aleksey
 * */
public class MyArray {

    /**
     * Метод меняет местами два объекта в массиве
     * @param array - массив
     * @param firstIndex - индекс 1-го объекта
     * @param secondIndex - индекс 2-го объекта
     * */
    public static <T extends Comparable<T>> void reverse (final int firstIndex, final int secondIndex, @NonNull T... array){
        T temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    /**
     * Метод преобразует массив в ArrayList
     * */
    public static <T> ArrayList toArrayList (@NonNull final T... a){
        return new ArrayList(Arrays.asList(a));
    }
}
