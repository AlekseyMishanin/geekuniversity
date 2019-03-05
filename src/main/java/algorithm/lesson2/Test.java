package algorithm.lesson2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * Класс для тестирования методов сортировки
 * @author - Mihanin Aleksey
 * */
public class Test {

    private List<Long> t1 = new ArrayList<>();  //список времени сортировки методом выбора на каждый отдельный эксперимент
    private List<Long> t2 = new ArrayList<>();  //список времени сортировки методом вставки на каждый отдельный эксперимент
    private final int size = 10000;             //размер массива
    private final int left = 1000;              //нижняя граница для формирования случайных чисел
    private final int right = 15000;            //верхняя граница для формирования случайных чисел
    private Random rnd = new Random();          //переменная для формирования случайного числа

    /**
     * Метод запускает поочередно тест сортировки методом выбора и вставки
     * */
    public void test(){

        testSectionSort();
        testInsertionSort();
    }

    /**
     * Метод генерирует массив с набором случайных элементов в рамках определенных границ
     * */
    private ArrayListDemo<Integer> createRandomArray(){
        ArrayListDemo<Integer> arrayListDemo = new ArrayListDemo<>(Integer.class);
        for (int i = 0; i < size; i++) {
            arrayListDemo.add(rnd.nextInt(right-left)+left);
        }
        return arrayListDemo;
    }

    /**
     * Метод тестирования сортировки методом выбора
     * */
    private void testSectionSort(){

        ArrayListDemo<Integer> arr = createRandomArray();

        long sysTimeA = System.currentTimeMillis();
        arr.selectionSort(Integer::compareTo);
        long sysTimeB = System.currentTimeMillis();

        t1.add(sysTimeB-sysTimeA);
    }

    /**
     * Метод тестирования сортировки методом вставки
     * */
    private void testInsertionSort(){

        ArrayListDemo<Integer> arr = createRandomArray();

        long sysTimeA = System.currentTimeMillis();
        arr.insertionSort(Integer::compareTo);
        long sysTimeB = System.currentTimeMillis();

        t2.add(sysTimeB-sysTimeA);
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();
        str.append("| № |\tselectionSort |\tinsertionSort |\n");

        char[] chars = new char[str.length()];
        Arrays.fill(chars,'-');
        str.append(chars).append('\n');
        for (int i = 0; i < t1.size(); i++) {
            str.append("| ").append(i);
            str.append(" |\t\t").append(t1.get(i));
            str.append("\t\t |\t\t").append(t2.get(i)).append(" |\n");
        }
        str.append(chars).append('\n');
        return str.toString();
    }
}
