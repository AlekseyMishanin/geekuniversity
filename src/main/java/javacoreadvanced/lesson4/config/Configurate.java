package javacoreadvanced.lesson4.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Класс Configurate. Содержит хост и номер порта
 * @author Mishanin Aleksey
 * */
public class Configurate {

    private static InetAddress inetAddress;
    private static int port;

    public static InetAddress getInetAddress() {
        return inetAddress;
    }

    public static int getPort() {
        return port;
    }

    public static void initialise(){
        try {
            inetAddress = InetAddress.getLocalHost();
            port = 8050;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
