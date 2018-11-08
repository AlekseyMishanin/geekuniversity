package javacoreadvanced.lesson6.client;

import javacoreadvanced.lesson6.config.Configuration;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socket;

    public Client(){
        Configuration.initialise();
    }

    public void start(){
        try {
            socket = new Socket(Configuration.getInetAddress().getHostName(),Configuration.getPotr());
            read(socket);
            write(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read(Socket socket){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while(!socket.isClosed()){
                        System.out.println("server: " + in.readLine());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void write(Socket socket){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
                    while (!socket.isClosed()){
                        String str = in.readLine();
                        out.println(str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
