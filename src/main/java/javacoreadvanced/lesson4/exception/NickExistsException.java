package javacoreadvanced.lesson4.exception;

public class NickExistsException extends Exception {

    private String nick;
    public NickExistsException(String nick){
        this.nick=nick;
    }

    @Override
    public String getMessage() {
        return "Ник " + nick + " занят";
    }
}
