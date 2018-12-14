package javacoreadvanced.lesson4.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.log4j.Logger;

/**
 * Класс Configurate. Содержит хост и номер порта
 * @author Mishanin Aleksey
 * */
public class Configurate {

    private static Logger LOGGER;
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
            LOGGER = Logger.getLogger("");
            inetAddress = InetAddress.getLocalHost();
            port = 8050;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }
}
