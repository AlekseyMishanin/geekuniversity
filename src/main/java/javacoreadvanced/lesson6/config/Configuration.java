package javacoreadvanced.lesson6.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Configuration {

    private static int potr;
    private static InetAddress inetAddress;

    public static int getPotr() {
        return potr;
    }

    public static InetAddress getInetAddress() {
        return inetAddress;
    }

    public static void initialise(){
        potr = 8080;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
