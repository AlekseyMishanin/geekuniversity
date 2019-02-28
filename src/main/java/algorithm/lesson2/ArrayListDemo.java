package algorithm.lesson2;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Класс упрощенного варианта ArrayList из каркаса коллекций.
 * @author Mishanin Aleksey
 * */
public class ArrayListDemo<T> implements Iterable<T>{

    private T[] array ;                             //массив элементов
    private final Class<T> cls;                     //класс
    private static final int RATIOINCREMENT = 2;    //коэффициент приращения массива (и обратной операции)
    private int size = 0;                           //кол-во элементов в массиве

    /**
     * Конструктор с параметрами.
     * @param cl - класс типа массива
     * */
    public ArrayListDemo(Class<T> cl){
        this.cls = cl;
        array = (T[]) Array.newInstance(cls,1); //создаем массив элементов. Конструкция позволит избежать явного приведения типа.
    }

    public int size(){
        return size;
    }

    public int length(){
        return array.length;
    }

    public T get(int index){
        if(index<0||index>size){
            throw new ArrayIndexOutOfBoundsException();
        }
        return array[index];
    }

    /**
     * Метод обновления элемента
     * @param index - порядковый номер в массиве куда нужно вставить
     * @param value - элемент который добавляем
     * */
    public void set(int index, T value){
        if(index<0||index>size){
            throw new ArrayIndexOutOfBoundsException();
        } else {
            array[index] = value;
        }
    }

    /**
     * Метод добавления элемента
     * @param value - элемент который добавляем
     * */
    public void add(T value){
        if(array.length==size){
            T[] temp = array;
            array = (T[]) Array.newInstance(cls,array.length*RATIOINCREMENT);
            System.arraycopy(temp,0,array,0,temp.length);
        }
        array[size]=value;
        size++;

    }

    /**
     * Метод изменения размера массива
     * @param capacity - новый размер
     * */
    public void resize(int capacity){
        T[] temp = array;
        array = (T[]) Array.newInstance(cls,capacity);
        System.arraycopy(temp,0,array,0,size);
    }

    /**
     * Метод поиска элемента
     * @param value - элемент который ищем
     * @return int - номер элемента если найден или -1
     * */
    public int indexOf(T value){
        for (int i = 0; i < array.length; i++) {
            if(array[i].equals(value)) return i;
        }
        return -1;
    }

    /**
     * Метод поиска элемента
     * @param value - элемент который ищем
     * @return: true - элемент найден, false - элемент не найден
     * */
    public boolean contains(T value){
        for (int i = 0; i < array.length; i++) {
            if(array[i].equals(value)) return true;
        }
        return false;
    }

    /**
     * Метод удаления элемента
     * @param value - удаляемый элемент
     * @return: true - элемент найден и удален, false - элемент не найден и не удален
     * */
    public boolean remove(T value){
        int index = indexOf(value);
        if(index!=-1){
            for (int i = index+1; i < array.length; i++) {
                array[i-1] = array[i];
            }
            size--;
            if(size<array.length/4&&size>0){
                resize(array.length/RATIOINCREMENT);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        if (array == null) return "null";

        int iMax = size - 1;
        if (iMax == -1) return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(String.valueOf(array[i]));
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T>{

        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor!=size;
        }

        @Override
        public T next() {
            if(!hasNext()){
                throw new IndexOutOfBoundsException();
            }
            return array[cursor++];
        }
    }

    private void exch(int x, int y){
        T temp = array[x];
        array[x]=array[y];
        array[y]=temp;
    }

    private boolean less(T a, T b, Comparator<T> cmp){
        return cmp.compare(a,b)<0;
    }

    /**
     * Метод сортировки методом выбора
     * @param cmp - компаратор для сравления двух элементов
     * */
    public void selectionSort(Comparator<T> cmp){
        for (int i = 0; i < size-1; i++) {
            int min = i;
            for (int j = i+1; j < size; j++) {
                if(less(array[j],array[min], cmp)){
                    min=j;
                }
            }
            exch(i,min);
        }
    }

    /**
     * Метод сортировки методом вставки
     * @param cmp - компаратор для сравления двух элементов
     * */
    public void insertionSort(Comparator<T> cmp){
        for (int i = 0; i < size; i++) {
            for (int j = i; j > 0; j--) {
                if(less(array[j],array[j-1],cmp)){
                    exch(j,j-1);
                } else {
                    break;
                }
            }
        }
        
    }

    /**
     * Метод двоичного поиска
     * */
    public boolean binarySearch(T value, Comparator<T> cmp){
        int low = 0;
        int high = size - 1;
        while (high>=low){
            int mid = low + (high-low)/2;
            if(cmp.compare(value,array[mid])<0){
                high = mid-1;
            } else if(cmp.compare(value,array[mid])>0){
                low = mid+1;
            } else {
                return true;
            }
        }
        return false;
    }
}
