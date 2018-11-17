package javacoreadvanced.lesson4.server;

import java.io.*;
import java.net.Socket;
import java.util.Iterator;
import java.util.Set;

/**
 * Класс ClientHandler
 * @author Mishanin Aleksey
 * */
public class ClientHandler implements Runnable{

    private Server server;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Thread thread;
    private String nick;
    private Set<String> blacklist;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()),4000);
            this.thread = new Thread(this);
            this.thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        try {
            //цикл авторизации
            while (true){
                String str = in.readLine();
                if(str.startsWith("/auth")){
                    String[] tokens = str.split(" ");
                    String nickname = server.getAuthService().getNickByLoginAndPass(tokens[1], tokens[2]);
                    if(nickname!=null) {
                        if(server.clientExists(nickname)){
                            sendMessage("/system Пользователь " + nickname + " авторизован. Используте другую пару логин/пароль.");
                        } else {
                            sendMessage("/authok " + nickname);
                            nick = nickname;
                            blacklist = server.getAuthService().getBlackList(nick);
                            server.subcribe(this);
                            break;
                        }
                    } else{
                        sendMessage("/system Неверный логин/пароль");
                    }
                }
            }
            //основной цикл
            while (true){
                String str = in.readLine();
                if(str.equals("/end")){
                    out.println("/serverClose");
                    break;
                } else if(str.startsWith("/w ")){
                    String[] tokens = str.split(" ", 3);
                    server.privateMessage(this,tokens[1],tokens[2]);
                } else if (str.startsWith("/blacklist ")){
                    String[] tokens = str.split(" ");
                    if (nick.equals(tokens[1])) continue;
                    addBlackList(tokens[1]);
                    sendMessage("/system Пользователь " + tokens[1] + " успешно добавлен в ЧС.");
                } else{
                    server.broadcastMessage(this,nick + ": " + str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        close();
    }

    public void sendMessage(String message){
        out.println(message);
    }

    private void close(){
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(socket.isClosed()) server.unsubcribe(this);
    }

    public String getNick() {
        return nick;
    }
    /**
     * Метод проверяет собеседника на наличие в черном списке 1-го лица
     * */
    public boolean chackBlackList(String nick){
        for (Iterator<String> i = blacklist.iterator(); i.hasNext();) {if(i.next().equals(nick)) return true;}
        return false;
    }

    /**
     * Метод добавляет собеседния в черный список 1-го лица
     * */
    public void addBlackList(String badnick){
        if(server.getAuthService().addBlackList(nick, badnick)){
            blacklist.add(badnick);
        }
    }
}
