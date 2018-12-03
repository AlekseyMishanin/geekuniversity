package javacoreprofession.lesson3.additionaltask;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс Server.
 * @author Mishanin Aleksey
 * */
public class Server implements Runnable{

    private ServerSocket server;
    private Thread th;

    public Server(){
        try {
            this.server = new ServerSocket(8090);
            th = new Thread(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод ждет подключения клиента. После установления соединения ожидает получение экзепляра класса Player. Если
     * объект успешно получен, в консоль выводится содержимое объекта.
     * */
    public void run(){
        try {
            Socket socket = server.accept();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Player player = (Player)in.readObject();
            if(player!=null){
                System.out.println("Server: OK");
                player.info();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        th.start();
    }
}
