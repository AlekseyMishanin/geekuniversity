package javacoreadvanced.lesson6.server;

import javacoreadvanced.lesson6.config.Configuration;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private Socket socket;

    public Server(){
        try {
            Configuration.initialise();
            serverSocket = new ServerSocket(Configuration.getPotr());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        try {
            System.out.println("Start server.");
            socket = serverSocket.accept();
            System.out.println("Client connect.");
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
                    while (!socket.isClosed()){
                        String str = in.readLine();
                        System.out.println("client: " + str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void write(Socket socket){
        try {
            PrintWriter out = new PrintWriter( new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while(!socket.isClosed()){
                String str = in.readLine();
                out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
