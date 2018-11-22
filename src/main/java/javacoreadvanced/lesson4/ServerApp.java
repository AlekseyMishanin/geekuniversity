package javacoreadvanced.lesson4;

import javacoreadvanced.lesson4.server.Server;

public class ServerApp {
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
