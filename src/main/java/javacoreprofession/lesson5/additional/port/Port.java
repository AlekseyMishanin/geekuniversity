package javacoreprofession.lesson5.additional.port;

import javacoreprofession.lesson5.additional.exception.StockroomEmptyException;
import javacoreprofession.lesson5.additional.interf.ActionWithShip;
import javacoreprofession.lesson5.additional.model.Goods;
import javacoreprofession.lesson5.additional.model.PortParameter;
import javacoreprofession.lesson5.additional.model.StatusPort;
import javacoreprofession.lesson5.additional.model.StatusShip;
import javacoreprofession.lesson5.additional.ship.Ship;

/**
 * Класс инкапсулирует основные характеристики порта
 * Класс реализует интерфейс ActionWithShip
 * @author Mishanin Aleksey
 * */
public abstract class Port implements ActionWithShip {

    //переменная с параметрами порта
    private PortParameter parameter;

    public Port(int clothing, int food, int fuel, Goods type) {
        parameter = new PortParameter();
        parameter.setClothing(clothing);
        parameter.setFood(food);
        parameter.setFuel(fuel);
        parameter.setType(type);
        parameter.setDistance((int)Math.random()*1500);     //рандомное расстояние между проливом и портом
        parameter.setStatus(StatusPort.ACTIVE);
    }

    @Override
    public void go(Ship ship){
        //проверяем тип товара порта на соответствие типу товара корабля
        if(!(ship.getType()==this.parameter.getType())) return;
        System.out.println(ship.getName() + " приближается к порту...");
        try {
            Thread.sleep(parameter.getDistance()/ship.getSpeed());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ship.getName() + " пришвартовался в порту...");
        switch (parameter.getStatus()){
            case ACTIVE:
                //если порт активен(склад не пуст)
                loadingGoods(ship);
                break;
            case INACTIVE:
                //если порт не активен (склад пуст)
                stockroomEmpty(ship);
                break;
        }
    }

    /**
     * Метод описывает механиз погрузки товара со склада на корабль.
     * @param ship - экземпляр класса производного от Ship
     * */
    private void loadingGoods(Ship ship){
        System.out.println("Началась погрузка " + parameter.getType() + " на " + ship.getName() + "...");
        try{
            //цикл крутиться пока трюм корабля не заполнен или пока склад не опустеет(будет исключение)
            while(ship.isFillingHold(parameter.getCAPACITYCRANE())){
                System.out.println("В трюм " + ship.getName() + " загружено " + parameter.getCAPACITYCRANE()
                        + " " + parameter.getType()+ "...");
                try {
                    //уменьшаем кол-во товара на складе порта
                    shippingFromWarehouse();
                    //загрущаем в трюм корабля товар отгруженный со склада
                    ship.loadingIntoHold(parameter.getCAPACITYCRANE());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (StockroomEmptyException e){
            //если склад опустел, блокируем переменную отвечающую за статус порта и присваиваем новый статус порту
            synchronized (parameter.getStatus()){
                System.out.println(e.getMessage());
                parameter.setStatus(StatusPort.INACTIVE);
            }
            //меняем статус корабля, чтобы корабль корректно завершил свой поток
            ship.setStatus(StatusShip.INACTIVE);
        }
        System.out.println("Погрузка " + parameter.getType() + " на " + ship.getName() + " завершена...");
    }

    /**
     * Метод описывает механиз уведомления вновь прибывшего корабля о том, что склад пуст
     * @param ship - экземпляр класса производного от Ship
     * */
    private void stockroomEmpty(Ship ship){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Сотрудник порта - \"Склад пуст\"");
        System.out.println(ship.getName() + " с пустым трюмом отправился в обратное плавание");
        ship.setStatus(StatusShip.INACTIVE);
    }

    /**
     * Метод описывает механиз отгрузки товара в соответствии с типом. Выполняется проверка на предмет отсутствия товара.
     * @exception  StockroomEmptyException - исключение генерируется при условии уменьшения кол-ва товара на складе до 0
     * */
    private void shippingFromWarehouse() throws StockroomEmptyException {
        switch (parameter.getType()){
            case FOOD:
                parameter.loadingFood(parameter.getFood()-parameter.getCAPACITYCRANE());
                System.out.println("На складе осталось " + parameter.getFood() + " " + parameter.getType());
                break;
            case FUEL:
                parameter.loadingFuel(parameter.getFuel()-parameter.getCAPACITYCRANE());
                System.out.println("На складе осталось " + parameter.getFuel() + " " + parameter.getType());
                break;
            case CLOTHES:
                parameter.loadingClothing(parameter.getClothing()-parameter.getCAPACITYCRANE());
                System.out.println("На складе осталось " + parameter.getClothing() + " " + parameter.getType());
                break;
        }
    }
}
