package javacoreadvanced.lesson2.basic.except;

/**
 * @author Mishanin Alecsey
 * */
public class MyArrayDataException extends Exception {
    public MyArrayDataException(int x, int y) {
        super("["+x+"]["+y+"] invalid data format");
    }
}
