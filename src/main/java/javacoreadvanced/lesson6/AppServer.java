package javacoreadvanced.lesson6;

import javacoreadvanced.lesson6.server.Server;

public class AppServer {
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
