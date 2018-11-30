package javacoreprofession.lesson3.additionaltask;

/*
Создать клиент и сервер.

Далее создать класс Player

Далее создать экземпляр класса Player, сереализовать объект, передать его по сети и десереализовать.
*/

public class Main {
    public static void main(String[] args) {

        Server server = new Server();
        server.start();
        Client client = new Client();
        client.start();
    }
}
