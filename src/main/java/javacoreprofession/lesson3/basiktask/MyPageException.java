package javacoreprofession.lesson3.basiktask;

/**
 * Класс исключительной ситуации, возникающей при выборе несуществующей страницы.
 * @author Mishanin Aleksey
 * */
public class MyPageException extends Exception {
    public MyPageException(){
        super("Not found page");
    }
}
