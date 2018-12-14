package javacoreadvanced.lesson4;

import org.junit.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {

    public com.mysql.jdbc.Connection connection = null;

    @Before
    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/chat";
            Assert.assertNotNull(url);
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url,"root", "12345");
            Assert.assertNotNull(connection);
        } catch (InstantiationException | SQLException |IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void echo(){
        try {
            final ServerSocket serverSocket = new ServerSocket(8050);
            Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final Socket socketForServer = serverSocket.accept();
                    PrintWriter outServer = new PrintWriter(new OutputStreamWriter(socketForServer.getOutputStream()),true);
                    BufferedReader inServer = new BufferedReader(new InputStreamReader(socketForServer.getInputStream()),4000);
                    String str = inServer.readLine();
                    outServer.println(str);
                } catch (IOException e) {
                    e.printStackTrace();
                    Assert.fail();
                }
            }
        });
        th1.start();
        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Socket socket = null;
                try {
                    socket = new Socket(serverSocket.getInetAddress().getHostName(),serverSocket.getLocalPort());
                } catch (IOException e) {
                    e.printStackTrace();
                    Assert.fail();
                }
                try {
                    PrintWriter outClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
                    BufferedReader inClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    outClient.println("echo");
                    String str = inClient.readLine();
                    System.out.println(str);
                    Assert.assertEquals("echo", str);
                } catch (IOException e) {
                    e.printStackTrace();
                    Assert.fail();
                }
            }
        });
        th2.start();
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @After
    public void desconnect(){
        Assert.assertNotNull(connection);
        try {
            connection.close();
            Assert.assertTrue(connection.isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
        }
        connection=null;
        Assert.assertNull(connection);
    }
}
