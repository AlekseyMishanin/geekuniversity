package javacoreprofession.lesson5.additional;

import javacoreprofession.lesson5.additional.interf.ActionWithShip;
import javacoreprofession.lesson5.additional.ship.Ship;
import java.util.concurrent.Semaphore;

/**
 * Класс описывает объект типа пролив. Класс реализует интерфейс ActionWithShip
 * @author Mishanin Aleksey
 * */
public class Channel implements ActionWithShip {

    protected int length;                   //длина пролива
    protected String description;           //краткое описание объекта
    private static Semaphore SEMAPHORE;
    public Channel() {
        this.length = 1100;
        this.description = "пролив (" + length + " метров)";
    }

    @Override
    public void go(Ship c) {
        try {
            try {
                //ограничение на возможность прохода по проливу не более двух кораблей
                if(SEMAPHORE==null) {SEMAPHORE = new Semaphore(2);}
                System.out.println(c.getName() + " подплыл ко входу в " + description);
                SEMAPHORE.acquire();
                System.out.println(c.getName() + " плывет через " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " покинул  " + description);
                SEMAPHORE.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
