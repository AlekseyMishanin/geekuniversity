package algorithm.lesson2;

/*
    Повторить реализацию АТД «список» с помощью структуры данных «массив» (не подглядывать, пытаться самим сделать).

    Провести эксперимент по сравнению эффективности алгоритмов сортировки:

    a) заполнить список случайными N целыми числами в диапазоне от a до b;
    b) запустить алгоритмы сортировки, засекая время их выполнения;
    c) повторить эксперимент M раз;
    d) данные вывести в таблицe.
*/

public class Main {

    public static void main(String[] args) {

        /*Test ts = new Test();
        for (int i = 0; i < 15; i++) {
            ts.test();
        }
        System.out.println(ts);*/

        ArrayListDemo<Character> arr = new ArrayListDemo<>(Character.class);
        arr.add('w');
        arr.add('t');
        arr.add('y');
        arr.add('b');
        arr.add('d');
        arr.add('f');
        arr.add('k');
        arr.add('z');
        arr.add('i');
        arr.add('p');
        arr.add('o');
        arr.add('m');
        arr.add('e');
        arr.add('a');
        System.out.println(arr);
        arr.quickSort(0,arr.size()-1,Character::compareTo);
        System.out.println(arr);
    }
}
