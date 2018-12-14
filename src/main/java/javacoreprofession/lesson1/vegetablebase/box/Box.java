package javacoreprofession.lesson1.vegetablebase.box;

import javacoreprofession.lesson1.vegetablebase.fruit.Fruit;
import javacoreprofession.lesson1.vegetablebase.inter.BoxActions;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс Box
 * Класс является аналогом коллекции. Параметром типа является тип данных наследуемый от класса Fruit.
 * Также класс Box реализует интерфейс BoxActions, т.е. определяет действия, характерные для коробок.
 * @author - Mishanin Aleksey
 * */
public class Box<T extends Fruit> implements BoxActions<T> {

    private List<T> box;            //переменная ссылающаяся на объект коробки
    private int size;               //кол-во элементов в коробке

    public Box(){
        box = new ArrayList<T>();
        size=0;
    }

    public Box(@NonNull final T... box){
        this();
        this.box.addAll(Arrays.asList(box));
        size = this.box.size();
    }

    /**
     * Метод подсчитывает суммарный вес фруктов в коробке. Если коробка пустая, возвращается 0. Если коробка не пустая,
     * умножаем вес первого элемента коллекции на кол-во элементов в коробке
     * */
    public double getWeight(){
        return box.isEmpty() ? 0.0 : box.get(0).getWeight()*size;
    }

    /**
     * Метод добавляет один фрукт в коробку
     * */
    @Override
    public boolean add(@NonNull final T value) {
        this.box.add(value);
        ++size;
        return true;
    }

    /**
     * Метод добавляет несколько фруктов в коробку. После добавления всех элементов аргумента в вызывающий объект
     * аргумент очищается.
     * */
    @Override
    public boolean pour(@NonNull final Box<T> value) {
        this.box.addAll(value.getBox());
        size=this.box.size();
        value.clear();
        return true;
    }

    /**
     * Метод сравнивает вес двух коробок. Если вес коробок совпадает, возвращается true. В противном случае - false.
     * @param o1 - первая коробка
     * @param o2 - вторая коробка
     * */
    public static<T extends Fruit> boolean compare(@NonNull final Box<?> o1, @NonNull final Box<?> o2) {
        return o1.getWeight()==o2.getWeight();
    }

    private List<T> getBox() {return box;}
    private void clear() {box = new ArrayList<T>(); size=0;}
}
