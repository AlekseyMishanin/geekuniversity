package javacoreadvanced.lesson1.secondary;

import lombok.NonNull;

import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author - Mishanin Aleksey
 * */
public class MyCharacter implements CountingLetter {

    /**
     * Метод сравнивает входную строку value с регулярным выражением и подсчитывает количество совпадений.
     * Параметры метода помечены аннотацией @NonNull для проверки на Null.
     * @param value - строка для манипуляций
     * @param regex - регулярное выражение
     * */
    @Override
    public void countingNumberChar(@NonNull final String value,@NonNull final String regex){
        //инициалицируем переменную для синтаксического аналица
        final StringTokenizer tokenizer = new StringTokenizer(value, "\n");
        //в цикле проверяем наличие лексем для последующего чтения
        while(tokenizer.hasMoreTokens()){
            //счетчик успешных совпадений с регулярным выражением
            int countChar = 0;
            /*возвращаем лексему в виде объекта типа String. Преобразуем объект
            типа String в символьный массив. Проходим в цикле по каждому символу*/
            for (final char ch : tokenizer.nextToken().toCharArray()) {
                //если заданная строка совпадает с регулярным выражением увеличиваем значение счетчика на 1
                if(String.valueOf(ch).matches(regex))  ++countChar;
            }
            //выводим значение счетчика в стандартный поток вывода
            System.out.println(countChar);
        }
    }

    /**
     * Метод сравнивает входную строку value с символами из массива шаблонов и подсчитывает количество совпадений.
     * Параметры метода помечены аннотацией @NonNull для проверки на Null.
     * @param value - строка для манипуляций
     * @param chars - массив шаблонных символов
     * */
    @Override
    public void countingNumberChar(@NonNull final String value,@NonNull final char[] chars){
        //разбиваем входную строку на части
        final String[] tokens = value.split("\n");
        //сортируем массив шаблонных символов по возрастанию
        Arrays.sort(chars);
        //проходим по массиву строк
        for (final String str : tokens) {
            //счетчик успешных совпадений символов входной строки с шаблоном
            int countChar = 0;
            //преобразуем строку массива в массив симовлов
            for (final char ch : str.toCharArray()) {
                //ишем в массиве шаблонов символ из строки массива. Если совпадение найдено, увеличиваем счетчик на 1
                if(Arrays.binarySearch(chars,ch)>=0) ++countChar;;
            }
            //выводим значение счетчика в стандартный поток вывода
            System.out.println(countChar);
        }
    }
}
