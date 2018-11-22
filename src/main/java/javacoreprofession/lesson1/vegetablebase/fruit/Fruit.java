package javacoreprofession.lesson1.vegetablebase.fruit;

import lombok.Getter;
import lombok.Setter;


/**
 * Абстрактный класс Fruit
 * @author Mishanin Aleksey
 * */
@Getter
@Setter
public abstract class Fruit {

    //вес фрукта
    private double weight;

    /**
     * Конструктор с параметрами
     * @param weight - вес фрукта
     * */
    public Fruit(double weight) {this.weight=weight;}
}
