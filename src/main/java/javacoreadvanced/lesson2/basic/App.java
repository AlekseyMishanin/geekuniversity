package javacoreadvanced.lesson2.basic;

import javacoreadvanced.lesson2.basic.array.MathArrays;
import javacoreadvanced.lesson2.basic.except.MyArrayDataException;
import javacoreadvanced.lesson2.basic.except.MyArraySizeException;

/*
1. Напишите метод, на вход которого подается двумерный строковый массив размером 4х4,
при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать.
Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или
текст вместо числа), должно быть брошено исключение MyArrayDataException – с детализацией,
в какой именно ячейке лежат неверные данные.
3. В методе main() вызвать полученный метод, обработать возможные исключения
MySizeArrayException и MyArrayDataException и вывести результат расчета.
*/

public class App {
    public static void main(String[] args) {

        //корректный массив
        final String[][] arrCurrent = {{"5","5","5","5"},{"4","4","4","4"},{"3","3","3","3"},{"3","3","3","3"}};
        //массив с некорректным размером
        final String[][] arrBadSize = {{"5","5","5","5"},{"4","4","4"},{"3","3","3","3"},{"3","3","3","3"}};
        //массив с некорректным форматом данных
        final String[][] arrBadFormat = {{"5","5","5","5"},{"4","no","4","4"},{"3","3","3","3"},{"3","3","3","3"}};

        try {
            System.out.println(MathArrays.sumValues(arrCurrent));
        } catch (MyArraySizeException|MyArrayDataException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(MathArrays.sumValues(arrBadSize));
        } catch (MyArraySizeException|MyArrayDataException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(MathArrays.sumValues(arrBadFormat));
        } catch (MyArraySizeException|MyArrayDataException e) {
            e.printStackTrace();
        }

    }
}
