package algorithm.lesson3;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * Класс стек.
 * */
public class MyStackArr<T> {

    private Class<T> cl;        //переменнатя типа Class
    private T[] array;          //переменная под массив объектов типа T
    private int size;           //размер массива

    /**
     * Конструктор с параметром
     * @param cl - объект типа Class, используемый для создания массива объектов
     * */
    public MyStackArr (Class<T> cl){

        this.cl=cl;
        array = (T[])Array.newInstance(cl,1);
        size = 0;
    }

    /**
     * Метод возвращает размер стека
     * */
    public int size(){
        return size;
    }

    /**
     * Массив проверяет стек на пустоту
     * */
    public boolean isEmpty(){
        return size==0;
    }

    /**
     * Метод меняет размер стека
     * @param capacity - новый размер
     * */
    private void resize (int capacity){
        T[] temp = array;
        array = (T[])Array.newInstance(cl,capacity);
        System.arraycopy(temp,0,array,0,size);
    }

    /**
     * Метод добавляет элемент в стек
     * @param value - добавляемый элемент
     * */
    public void push(T value){

        if(size==array.length){
            resize(size*2);
        }
        array[size++]=value;
    }

    /**
     * Метод возвращает элемент с вершины стека
     * */
    public T peek(){

        if(isEmpty()){
            throw new NoSuchElementException("Stack is empty");
        }
        return array[size - 1];
    }

    /**
     * Метод возвращает элемент с вершины стека, уменьяшая размер стека на 1
     * */
    public T pop (){
        if(isEmpty()){
            throw new NoSuchElementException("Stack is empty");
        }
        T temp = array[size - 1];
        size--;
        if(size == array.length / 4 && size > 0) {
            resize(array.length / 2);
        }
        return temp;
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();
        for (int i = size-1; i >=0 ; i--) {
            if(i==size-1) {str.append("[ ");}
            str.append(array[i].toString());
            if(i!=0) {str.append(", ");}
            if(i==0) {str.append(" ]");}
        }
        return str.toString();
    }
}
