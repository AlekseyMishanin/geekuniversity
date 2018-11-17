package javacoreadvanced.lesson4.server;

import javacoreadvanced.lesson4.config.Configurate;
import javacoreadvanced.lesson4.db.AuthService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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

    protected void broadcastMessage(String message){
        for (ClientHandler client: clients) {
            client.sendMessage(message);
        }
    }
    protected void privateMessage(String nick, String message){
        for (ClientHandler client: clients) {
            if(client.getNick().equals(nick)) {client.sendMessage(message); break;}
        }
    }
    public void subcribe(ClientHandler client){clients.add(client);}
    public void unsubcribe(ClientHandler client){clients.remove(client);}
    public AuthService getAuthService() { return authService; }
    public boolean clientExists(String nick){
        boolean result = false;
        for (ClientHandler client: clients) {
            if(client.getNick().equals(nick)) {result = true; break;}
        }
        return result;
    }
}
