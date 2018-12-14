package javacoreprofession.lesson7.additional.second;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class CreateTable {
    private Connection connection;
    private Statement statement;

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/chat", "root", "12345");
            statement = connection.createStatement();
        } catch (ClassNotFoundException| SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try {
            statement.close();
            connection.close();
            statement=null;
            connection=null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void prepareTable(Class cl){

        HashMap<Class,String> hm = new HashMap<>();
        hm.put(int.class,"INTEGER");
        hm.put(String.class,"TEXT");

        try{
            connect();
            String tableName = ((xTable)cl.getAnnotation(xTable.class)).name();
            statement.execute("DROP TABLE IF EXISTS " + tableName + ";");
            String query = "CREATE TABLE IF NOT EXISTS " + tableName + " (";
            Field[] fields = cl.getDeclaredFields();

            for (Field field: fields) {
                if(field.isAnnotationPresent(xField.class)){
                    query+=field.getName() + " " + hm.get(field.getType()) + ", ";
                }
            }
            query=query.substring(0,query.length()-2);
            query+=");";
            statement.execute(query);
            disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
