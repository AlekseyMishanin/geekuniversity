package javacoreadvanced.lesson4.db;

import javacoreadvanced.lesson4.exception.LoginExistsException;
import javacoreadvanced.lesson4.exception.NickExistsException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

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
        String sql = String.format("SELECT nick, password FROM chat.user WHERE login ='%s'", login);
        int passHash = password.hashCode();
        ResultSet res = null;
        try {
            res = statement.executeQuery(sql);
            if(res.next()){
                String nick = res.getString(1);
                int passBd = res.getInt(2);
                if(passHash==passBd) return nick;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set<String> getBlackList(String nick){
        String sql = String.format("SELECT black_nick FROM chat.blacklist WHERE chat.blacklist.user_nick ='%s'", nick);
        Set<String> result = new HashSet<>();
        try {
            ResultSet res = statement.executeQuery(sql);
            if(res.next()){
                result.add(res.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean addBlackList(String from, String badnick){
        String sql = String.format("INSERT INTO `chat`.`blacklist` (`user_nick`, `black_nick`) VALUES('%s', '%s')", from, badnick);
        boolean res = false;
        try {
            res = statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    private void checkNick(final String nick) throws NickExistsException {
        String sql = String.format("SELECT nick FROM chat.user WHERE nick ='%s'", nick);
        try {
            ResultSet res = statement.executeQuery(sql);
            if(res.next()){
                throw new NickExistsException(res.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkLogin(final String login) throws LoginExistsException {
        String sql = String.format("SELECT login FROM chat.user WHERE login ='%s'", login);
        try {
            ResultSet res = statement.executeQuery(sql);
            if(res.next()){
                throw new LoginExistsException(res.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(final String nick, final String login, final String pass) throws NickExistsException, LoginExistsException {
        checkNick(nick);
        checkLogin(login);
        String sql = String.format("INSERT INTO `chat`.`user` (`login`, `nick`, `password`) VALUES ('%s', '%s', '%s')", login,nick,pass.hashCode());
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
