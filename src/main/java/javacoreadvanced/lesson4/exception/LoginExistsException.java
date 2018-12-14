package javacoreadvanced.lesson4.exception;

public class LoginExistsException extends Exception {

    private String login;
    public LoginExistsException(String login){
        this.login=login;
    }

    @Override
    public String getMessage() {
        return "Логин " + login + " занят";
    }
}
