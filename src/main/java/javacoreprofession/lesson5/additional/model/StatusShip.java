package javacoreprofession.lesson5.additional.model;

/**
 * Перечисление описывающее статусы корабля
 * @author Mishanin Aleksey
 * */
public enum StatusShip {

    FULL,       //корабль активный, трюм заполнен
    EMPTY,      //корабль активный, трюм пустой
    INACTIVE;   //корабль не активный, трюм заполнен/пустой, корабль останется в городе(цикл итераций прервется)
}
