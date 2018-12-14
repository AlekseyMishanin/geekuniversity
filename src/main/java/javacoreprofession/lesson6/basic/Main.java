package javacoreprofession.lesson6.basic;

/*
* Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
* Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
* идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку, иначе в методе
* необходимо выбросить RuntimeException. Написать набор тестов для этого метода (по 3-4 варианта входных данных).
* Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
* Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной четверки или единицы,
* то метод вернет false; Написать набор тестов для этого метода (по 3-4 варианта входных данных).
* */

import lombok.NonNull;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        final Integer[] array1 = { 1, 2, 4, 4, 2, 3, 4, 1, 7};
        final Integer[] array2 = { 1, 2, 2, 3, 1, 7};
        final Integer[] array3 = { 2, 2, 3, 7};

        Integer[] arr = Main.updateArray(4, array1);
        System.out.println(Arrays.toString(arr));
        System.out.println(Main.findNumberInArray(array1));
        System.out.println(Main.findNumberInArray(array3));
        arr = Main.updateArray(4, array2);
    }

    public static <T extends Number, V extends T> T[] updateArray(final @NonNull V value, final @NonNull T... arr){
        int findIndex;
        if((findIndex = Arrays.asList(arr).lastIndexOf(value)) == -1){
            throw new MyRuntimeException();
        } else{
            return Arrays.copyOfRange(arr, findIndex + 1,arr.length);
        }
    }

    public static <T extends Number> boolean findNumberInArray(final @NonNull T... arr){
        String str = Arrays.toString(arr);
        return str.matches("^.*(?=.*[1])(?=.*[4]).*$");
    }
}

