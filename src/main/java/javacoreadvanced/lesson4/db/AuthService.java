package javacoreadvanced.lesson4.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthService extends DB_mysql{

    private Statement statement = null;

    public AuthService(){
        setURL("localhost", "chat", 3306);
        connect("root", "12345");
    }

    public void connect() {
        try {
            statement = getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getNickByLoginAndPass(String login, String password) {
        String sql = String.format("SELECT nick FROM chat.user WHERE login ='%s' AND password = '%s'", login, password);
        ResultSet res = null;
        try {
            res = statement.executeQuery(sql);
            return res.next() ? res.getString(1) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
