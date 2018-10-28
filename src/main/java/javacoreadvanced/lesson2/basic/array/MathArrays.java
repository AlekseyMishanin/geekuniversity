package javacoreadvanced.lesson2.basic.array;

import javacoreadvanced.lesson2.basic.except.MyArrayDataException;
import javacoreadvanced.lesson2.basic.except.MyArraySizeException;

/**
 * @author Mishanin Alecsey
 * */
public class MathArrays {

    /**
     * Метод принимает на вход двухметный массив строк размера 4*4. Приводит элементы массива к типу int и
     * складывает полученные значения. Метод возвращает сумму значений элементов массива.
     * @exception MyArraySizeException - пробрасывается если на вход подается массив размера отличного от 4*4
     * @exception MyArrayDataException - пробрасывается если элемент массива невозможно привести к типу int
     * @param value - двухмерный массив строк
     * */
    public static int sumValues (final String[][] value) throws MyArraySizeException, MyArrayDataException {
        //проверяем размер массива
        if(value.length!=4) throw new MyArraySizeException();
        //переменная результата
        int result = 0;
        //переменные для определения позиции элемента в двухмерном массиве
        int i = 0, j = 0;
        for (final String[] stringArray : value) {
            //проверяем размер массива
            if(stringArray.length!=4) throw new MyArraySizeException();
            //обновляем индексы
            ++i; j=0;
            for (final String string : stringArray) {
                try {
                    //обновляем индекс
                    ++j;
                    //суммируем значения элементов массива
                    result += Integer.parseInt(string);
                } catch (NumberFormatException e){
                    throw new MyArrayDataException(i,j);
                }
            }
        }
        return result;
    }
}
