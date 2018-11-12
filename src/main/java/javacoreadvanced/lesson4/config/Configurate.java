package javacoreadvanced.lesson4.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
