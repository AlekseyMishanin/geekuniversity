package javacoreprofession.lesson5.additional;

import javacoreprofession.lesson5.additional.exception.ShipCapacityException;
import javacoreprofession.lesson5.additional.port.PortClothes;
import javacoreprofession.lesson5.additional.port.PortFood;
import javacoreprofession.lesson5.additional.port.PortFuel;
import javacoreprofession.lesson5.additional.ship.Ship;
import javacoreprofession.lesson5.additional.ship.ShipClothes;
import javacoreprofession.lesson5.additional.ship.ShipFood;
import javacoreprofession.lesson5.additional.ship.ShipFuel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws ShipCapacityException {
        Itinerary itinerary = new Itinerary(new City(), new Channel(), new PortClothes(2700)
                , new PortFood(5900), new PortFuel(8500), new Channel());
        Ship[] ships = {new ShipClothes("Санта Моника",500,itinerary),
                new ShipFuel("Летучий голландец",500,itinerary),
                new ShipFood("Барбарроса",500,itinerary),
                new ShipFuel("Гуччи",500,itinerary)};
        ExecutorService executor = Executors.newFixedThreadPool(ships.length);
        for (int i = 0; i < ships.length; i++) {
            executor.execute(ships[i]);
        }
        executor.shutdown();
    }
}
