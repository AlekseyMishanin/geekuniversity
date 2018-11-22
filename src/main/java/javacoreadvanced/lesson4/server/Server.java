package javacoreadvanced.lesson4.server;

import javacoreadvanced.lesson4.config.Configurate;
import javacoreadvanced.lesson4.db.AuthService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Класс Server
 * @author Mishanin Aleksey
 * */
public class Server implements Runnable{

    private ServerSocket serverSocket = null;
    private List<ClientHandler> clients;
    private Thread thread;
    private AuthService authService;

    public Server(){
        Configurate.initialise();
        try {
            authService = new AuthService();
            authService.connect();
            serverSocket = new ServerSocket(Configurate.getPort());
            System.out.println("Server start");
            clients = new CopyOnWriteArrayList<>();
            thread = new Thread(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        while (true){
            try {
                //authService.addUser("login1", "pass1", "nick1");
                //authService.addUser("login2", "pass2", "nick2");
                //authService.addUser("login3", "pass3", "nick3");
                Socket socket = serverSocket.accept();
                System.out.println("Client connect");
                new ClientHandler(this,socket);
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    serverSocket.close();
                } catch (IOException o) {
                    e.printStackTrace();
                }
                authService.disconnect(authService.getConnection());
                break;
            }
        }
    }

    public void start(){
        thread.start();
    }

    /**
     * Метод отправляет широковещательное сообщение. Реализована проверка на наличие в ЧС отправителя и в ЧС получателя.
     * */
    protected void broadcastMessage(ClientHandler from, String message){
        for (ClientHandler client: clients) {
            if(!from.chackBlackList(client.getNick())&&
                    !client.chackBlackList(from.getNick())
            ) client.sendMessage(message);
        }
    }

    /**
     * Метод отправляет личное сообщение.
     * */
    protected void privateMessage(ClientHandler from, String to, String message){
        //запрещаем отправку личных сообщений самому себе
        if(from.getNick().equals(to)) return;
        if(clientExists(to)){
            for (ClientHandler client: clients) {
                if(client.getNick().equals(to)&&from.chackBlackList(client.getNick())) {
                    from.sendMessage("/system Пользователь " + to + " в ЧС. Отправка сообщений не возможна."); return;}
                if(client.getNick().equals(to)&&client.chackBlackList(from.getNick())) {
                    from.sendMessage("/system Пользователь " + to + " добавил Вас в ЧС. Отправка сообщений не возможна."); return;}
                if(client.getNick().equals(to)) {
                    client.sendMessage("from " + from.getNick() + ": " + message); break;}
            }
            from.sendMessage("to " + to + ": " + message);
        } else {
            from.sendMessage("/system Пользователь " + to + " не авторизован.");
        }
    }
    public void subcribe(ClientHandler client){clients.add(client); broadcastClientList();}
    public void unsubcribe(ClientHandler client){clients.remove(client); broadcastClientList();}
    public AuthService getAuthService() { return authService; }

    /**
     * Метод проверяет ник на наличие в списке авторизованных пользователей
     * */
    public boolean clientExists(String nick){
        boolean result = false;
        for (ClientHandler client: clients) {
            if(client.getNick().equals(nick)) {result = true; break;}
        }
        return result;
    }

    /**
     * Метод осуществляет широковещательную рассылку списка авторизованных пользователей
     * */
    public void broadcastClientList(){
        StringBuilder sb = new StringBuilder();
        sb.append("/clientlist ");
        for (ClientHandler client: clients) {
            sb.append(client.getNick() + " ");
        }
        String cliStr = sb.toString();
        for (ClientHandler client: clients) {
            client.sendMessage(cliStr);
        }
    }
}
