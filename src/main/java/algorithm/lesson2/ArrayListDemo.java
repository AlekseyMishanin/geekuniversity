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

    /**
     * Внутренний класс реализующий интерфейс Iterator
     * */
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

    /**
     * Метод перестановки двух элементов местами
     * */
    private void exch(int x, int y){
        T temp = array[x];
        array[x]=array[y];
        array[y]=temp;
    }

    /**
     * Метод сравнения двух элементов
     * */
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
     * Метод сортировки методом вставки для заданного отрезка
     * @param cmp - компаратор для сравления двух элементов
     * */
    private void insertionSort(int left, int right, Comparator<T> cmp){
        for (int i = left; i < right; i++) {
            for (int j = i; j > left; j--) {
                if(less(array[j],array[j-1],cmp)){
                    exch(j,j-1);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * Метод сортировки Шелла
     * */
    public void shellSort(Comparator<T> cmp){
        int inner = 0, outer = 0;
        T temp;
        int h = 1;

        //определение интервальных последовательностей при помощи уравнение Кнута
        while(h<=size/3) h = 3*h + 1;

        while (h>0){
            for (outer = h;  outer<size ; outer++) {
                temp = array[outer];
                inner=outer;
                while(inner>h-1&&less(temp, array[inner-h], cmp)){
                    array[inner] = array[inner-h];
                    inner -= h;
                }
                array[inner] = temp;
            }
            h = (h -1)/3;
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

    /**
     * Метод быстрой сортировки
     * */
    public void quickSort(int left, int right, Comparator<T> cmp){

        int size = right - left + 1;
        if(size<=9){
            insertionSort(left,right, cmp);
        } else {
            T median = medianOf3(left,right, cmp);
            int partition = partition(left,right,median,cmp);
            quickSort(left,partition-1,cmp);
            quickSort(partition+1,right,cmp);
        }
    }

    /**
     * Метод определения медианы по трем точкам
     * */
    private T medianOf3(int left, int right, Comparator<T> cmp){

        int center = left + (right-left)/2;
        if(less(array[center],array[left],cmp)) exch(center,left);      //значение центрального элемента меньше левого
        if(less(array[right],array[left],cmp)) exch(right,left);        //значение правого элемента меньше левого
        if(less(array[right],array[center],cmp)) exch(center,right);    //значение правого элмента меньше центрального
        exch(center, right-1); //перемещаем опорный элемент на правый край
        return array[right-1];
    }

    /**
     * Метод разбиения массива на две группы: слева с меньшими ключами, справа с большими ключами
     * */
    private int partition(int left, int right, T pivot, Comparator<T> cmp){

        int leftPtr = left;
        int rightPrt = right - 1;

        while (true){
            while (less(array[++leftPtr],pivot,cmp));   //поиск в левом подмножестве элемента большего чем опорный
            while (less(pivot,array[--rightPrt],cmp));  //поиск в правом подмножестве элемента меньше чем опорный
            if(rightPrt<=leftPtr) {
                break;                                  //если указатели сошлись разбиение окончено
            } else {
                exch(leftPtr,rightPrt);                 //в противном случае меняем элементы местами
            }
        }
        exch(leftPtr,right-1);                      //восстанавливаем положение опорного элемента
        return leftPtr;
    }

}
