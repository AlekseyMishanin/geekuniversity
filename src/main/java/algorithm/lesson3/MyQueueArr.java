package algorithm.lesson3;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * Класс очереди
 * */
public class MyQueueArr<T> {

    private Class<T> cl;        //переменнатя типа Class
    private T[] array;          //переменная под массив объектов типа T
    private int size;           //размер массива
    private int start;          //номер первого элемента
    private int end;            //номер последнего элемента

    /**
     * Конструктор с параметром
     * @param cl - объект типа Class, используемый для создания массива объектов
     * */
    public MyQueueArr(Class cl) {
        this.cl=cl;
        size = start = end = 0;
        array = (T[])Array.newInstance(cl,1);
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
     * Метод добавляет элемент в конец очереди
     * @param value - элемент добавляемый в очередь
     * */
    public void enqueue(T value){
        if(size==array.length){
            resize(size*2);
        }
        array[end++] = value;
        end %= array.length;
        size++;
    }

    /**
     * Метод возвращает элемент из начала очереди, удаляя данный элемент из очереди
     * */
    public T dequeue(){
        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty.");
        }
        T item = array[start];
        size--;
        start++;
        start %= array.length;
        if(size == array.length/4 && size > 0){
            resize(size/2);
        }
        return item;
    }

    /**
     * Метод возвращает элемент из начала очереди, не удаляя данный элемент из очереди
     * */
    public T peek(){

        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty.");
        }
        return array[start];
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
