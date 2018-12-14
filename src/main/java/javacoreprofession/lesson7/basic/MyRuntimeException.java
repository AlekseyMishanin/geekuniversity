package javacoreprofession.lesson7.basic;

/**
 * Класс непроверяемой исключительной ситуации, описывающей ситуацию при которой класс с тестами содержит
 * более одного метода с аннотациями AfterSuite или BeforeSuite
 * @author - Mishanin Aleksey
 * */
public class MyRuntimeException extends RuntimeException {
    public MyRuntimeException(String text){
        super(text);
    }
}
