package javacoreprofession.lesson1;

/*
1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
2. Написать метод, который преобразует массив в ArrayList;
3. Большая задача:
Есть классы Fruit -> Apple, Orange (больше фруктов не надо);
Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну
коробку нельзя сложить и яблоки, и апельсины;
Для хранения фруктов внутри коробки можно использовать ArrayList;
Сделать метод getWeight(), который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
(вес яблока – 1.0f, апельсина – 1.5f. Не важно, в каких это единицах);
Внутри класса Коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую
подадут в compare в качестве параметра, true – если она равны по весу, false – в противном случае
(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую (помним про сортировку фруктов:
нельзя яблоки высыпать в коробку с апельсинами). Соответственно, в текущей коробке фруктов не остается,
а в другую перекидываются объекты, которые были в этой коробке;
Не забываем про метод добавления фрукта в коробку.
*/

/*
Решение 1-й задачи: метод MyArray.reverse();
Решение 2-й задачи: метод MyArray.toArrayList();
Решение 3-й задачи: класс Fruit, Apple, Orange, Box, интерфейс BoxActions.
*/
import javacoreprofession.lesson1.vegetablebase.box.Box;
import javacoreprofession.lesson1.vegetablebase.fruit.Apple;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Integer[] intArray = new Integer[10];
        for (int i = 0; i < intArray.length; i++) {intArray[i]=i;}
        System.out.println(Arrays.toString(intArray));

        //пример решения первой задачи
        MyArray.reverse(5, 8, intArray);
        System.out.println(Arrays.toString(intArray));

        //пример решения второй задачи
        ArrayList<Integer> arrayList = MyArray.toArrayList(intArray);
        System.out.println(arrayList);

        //пример решения третьей задачи
        Apple[] apples1 = new Apple[5];
        for (int i = 0; i < apples1.length; i++) {apples1[i] = new Apple();}
        Apple[] apples2 = new Apple[2];
        for (int i = 0; i < apples2.length; i++) {apples2[i] = new Apple();}

        Box<Apple> boxApple1 = new Box<>(apples1);
        Box<Apple> boxApple2 = new Box<>(apples2);
        System.out.println("Сравнение box1 и box2 = " + Box.compare(boxApple1, boxApple2));
        System.out.println("Вес box1 = " + boxApple1.getWeight());
        boxApple1.add(new Apple());
        boxApple1.add(new Apple());
        boxApple1.add(new Apple());
        System.out.println("Вес box1 = " + boxApple1.getWeight());
        System.out.println("Вес box2 = " + boxApple2.getWeight());
        boxApple1.pour(boxApple2);
        System.out.println("Вес box1 = " + boxApple1.getWeight());
        System.out.println("Вес box2 = " + boxApple2.getWeight());





    }
}
