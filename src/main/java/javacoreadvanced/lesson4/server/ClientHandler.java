package javacoreadvanced.lesson4.server;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{

    private Server server;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Thread thread;
    private String nick;

    public ClientHandler(Server server,Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.thread = new Thread(this);
            this.thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        try {
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
                            server.subcribe(this);
                            break;
                        }
                    } else{
                        sendMessage("/system Неверный логин/пароль");
                    }
                }
            }
            while (true){
                String str = in.readLine();
                if(str.equals("/end")){
                    out.println("/serverClose");
                    break;
                }
                if(str.startsWith("/w ")){
                    String[] tokens = str.split(" ");
                    if(server.clientExists(tokens[1])){
                        server.privateMessage(tokens[1], tokens[2]);
                    } else {
                        sendMessage("/system Пользователь " + tokens[1] + " не авторизован.");
                    }
                } else {
                    server.broadcastMessage(nick + ": " + str);
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
}
