package javacoreprofession.lesson3.additionaltask;

import java.io.*;
import java.net.Socket;

/**
 * Класс Client.
 * @author Mishanin Aleksey
 * */
public class Client implements Runnable{

    private Socket socket;
    private Thread th;

    public Client(){
        try {
            socket = new Socket("localhost", 8090);
            th = new Thread(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * В методе создается экземпляр класс Player. Полученный экземпляр класса Player записывается в файл.
     * Далее данные читаются из файла и пишутся в сокет
     * */
    public void run(){
        Player player = new Player("Jack", 25);
        File file = new File("/", "player");
        try {
            ObjectOutputStream toFile = new ObjectOutputStream(new FileOutputStream(file));
            ObjectInputStream fromFile = new ObjectInputStream(new FileInputStream(file));
            ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
            toFile.writeObject(player);
            toServer.writeObject(fromFile.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        th.start();
    }
}
