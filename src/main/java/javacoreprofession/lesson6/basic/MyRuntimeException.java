package javacoreprofession.lesson6.basic;

public class MyRuntimeException extends RuntimeException {
    public MyRuntimeException(){
        super("Not found value in array");
    }
}
