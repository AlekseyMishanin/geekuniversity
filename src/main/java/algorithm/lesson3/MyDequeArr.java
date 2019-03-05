package algorithm.lesson3;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * Класс двухсторонней очереди
 * */
public class MyDequeArr<T> {

    private Class<T> cl;        //переменнатя типа Class
    private T[] array;          //переменная под массив объектов типа T
    private int size;           //размер массива
    private int start;          //номер первого элемента
    private int end;            //номер последнего элемента

    /**
     * Конструктор с параметром
     * @param cl - объект типа Class, используемый для создания массива объектов
     * */
    public MyDequeArr(Class cl) {
        this.cl=cl;
        size = start = end = 0;
        array = (T[]) Array.newInstance(cl,1);
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    private void resize(int capacity){

        T[] temp = (T[])Array.newInstance(cl,size*2);
        for (int i = 0; i < size; i++) {
            temp[i] = array[(start + i)%array.length];
        }
        array = temp;
        start = 0;
        end = size;
    }

    /**
     * Метод добавляет элемент в начало очереди
     * @param value - элемент который добавляем в очередь
     * */
    public void insertLeft(T value){

        if(size==array.length){
            resize(size * 2);
        }
        if(--start < 0) start = array.length-1;
        array[start] = value;
        size++;
    }

    /**
     * Метод добавляет элемент в конец очереди
     * @param value - элемент который добавляем в очередь
     * */
    public void insertRight(T value){

        if(size == array.length){
            resize(size * 2);
        }
        array[end++] = value;
        end %= array.length;
        size++;
    }

    /**
     * Метод возвращает элемент из начала очереди, не удаляя данный элемент из очереди
     * */
    public T peekLeft(){

        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty.");
        }
        return array[start];
    }

    /**
     * Метод возвращает элемент с конца очереди, не удаляя данный элемент из очереди
     * */
    public T peekRight(){

        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty.");
        }
        return array[end];
    }

    /**
     * Метод возвращает элемент из начала очереди, удаляя данный элемент из очереди
     * */
    public T removeLeft(){

        if(isEmpty()){
            throw new NoSuchElementException("Deque is empty");
        }
        T item = array[start++];
        size--;
        start%=array.length;
        if(size == array.length / 4 && size > 0){
            resize(array.length / 2);
        }
        return item;
    }

    /**
     * Метод возвращает элемент с конца очереди, удаляя данный элемент из очереди
     * */
    public T removeRight(){

        if(isEmpty()){
            throw new NoSuchElementException("Deque is empty");
        }
        T item = array[end--];
        size--;
        if(end<0) end = array.length-1;
        if(size == array.length / 4 && size > 0){
            resize(array.length / 2);
        }
        return item;
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if(i==0) {str.append("[");}
            str.append(array[(i+start)%array.length].toString());
            if(i!=size-1) {str.append(", ");}
            if(i==size-1) {str.append("]");}

        }
        return str.toString();
    }
}
