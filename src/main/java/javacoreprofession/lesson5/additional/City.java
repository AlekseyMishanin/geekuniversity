package javacoreprofession.lesson5.additional;

import javacoreprofession.lesson5.additional.exception.EndVoyageException;
import javacoreprofession.lesson5.additional.interf.ActionWithShip;
import javacoreprofession.lesson5.additional.model.StatusShip;
import javacoreprofession.lesson5.additional.ship.Ship;

/**
 * Класс описывает объект типа город. Данный объект является пунктом отправления для корабля и последующего прибытия
 * с целью разгрузки
 * */
public class City implements ActionWithShip {

    private static String name;         //наименование города
    private static int distance;        //расстояние от города до пролива
    static {
        name = "Veracruz";
        distance = 1500;
    }

    @Override
    public void go(Ship ship) throws EndVoyageException {
        while(true){
            switch (ship.getStatus()){
                //трюм корабля полон, склад поставщика не пуст
                case FULL:
                    System.out.println(ship.getName() + " держит курс на " + name);
                    try {
                        Thread.sleep(distance / ship.getSpeed() * 1000);
                        System.out.println(ship.getName() + " пришвартовался к пристани " + name + ". Началась разгрузка.");
                        Thread.sleep((int) (Math.random() * 1000));
                        System.out.println("Отгружено " + ship.getFillingHold() + " " + ship.getType());
                        //очищаем трюм корабля от товара
                        ship.setFillingHold(0);
                        //присваиваем новый статус кораблю
                        ship.setStatus(StatusShip.EMPTY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                //трюм корабля пуст, склад поставщика не пуст
                case EMPTY:
                    System.out.println(ship.getName() + " отшвартовался и отрпавился за новой партией " + ship.getType());
                    try {
                        Thread.sleep(distance / ship.getSpeed() * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //выходим из цикла и оправляемся за новой партией товара
                    return;
                //трюм корабля или пуст, или полон, склад поставщика точно пуст
                case INACTIVE:
                    System.out.println(ship.getName() + " держит курс на " + name);
                    try {
                        Thread.sleep(distance / ship.getSpeed() * 1000);
                        System.out.println(ship.getName() + " пришвартовался к пристани " + name + ". Началась разгрузка.");
                        Thread.sleep((int) (Math.random() * 1000));
                        System.out.println("Отгружено " + ship.getFillingHold() + " " + ship.getType());
                        ship.setFillingHold(0);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        //генерируем исключительную ситуацию описывающую конец путешествия
                        throw new EndVoyageException();
                    }
            }
        }
    }
}
