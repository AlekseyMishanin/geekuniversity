package algorithm.lesson4;

import lombok.NonNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс инкапсулирует двунаправленных двухсвязанный список. Класс релазиует интерфес Iterable
 * предоставляя возможность использовать цикл for в стиле for each
 * */

public class MyLinkedList<T> implements Iterable<T>{

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator<T> implements Iterator<T>{

        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if(!(current!=null)){
                throw new NoSuchElementException();
            }
            T item = (T)current.item;
            current = current.next;
            return item;
        }
    }

    /**
     * Внутренний класс инкапсулирует элемент списка. Элемент списка содержит: 1. данные, 2. ссылку на следующий элемент,
     * 3. ссылку на предыдущий элемент
     * */
    private class Node{

        T item;         //данные
        Node next;      //ссылка на следующий элемент
        Node previos;   //ссылка на предыдущий элемент

        public Node(T item, Node next, Node previos){
            this.item = item;
            this.next = next;
            this.previos = previos;
        }

        @Override
        public String toString() {
            return item.toString();
        }
    }

    private Node first = null;      //ссылка на первый элемент списка
    private Node last = null;       //ссылка на последний элемент списка
    private int size = 0;           //кол-во элементов в списке

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Метод возвращает данные первого элемента списка
     * */
    public T getFirst(){

        if(isEmpty())
            throw new NoSuchElementException();
        return first.item;
    }

    /**
     * Метод добавляет элемент в начало списка
     * @param item - данные, которые будут храниться в новом элементе
     * */
    public void addFirst(@NonNull final T item){

        Node newElement = new Node(item, first, null);
        if(isEmpty()) {
            last = newElement;
        } else {
            first.previos = newElement;
        }
        first = newElement;
        size++;
    }

    /**
     * Метод удаляет первый элемент из списка, возвращая данные содержащиеся в удаляемом элементе
     * */
    public T removeFirst(){

        if(isEmpty()){
            throw new NoSuchElementException();
        }
        Node old = first;
        first = old.next;
        old.next = null;
        --size;
        if(isEmpty()){
            last = null;
        } else {
            first.previos=null;
        }
        return old.item;
    }

    /**
     * Метод возвращает данные последнего элемента списка
     * */
    public T getLast(){

        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return last.item;
    }

    /**
     * Метод добавляет элемент в конец списка
     * @param item - данные, которые будут храниться в новом элементе
     * */
    public void addLast(@NonNull final T item){

        Node old = last;
        Node newElement = new Node(item, null, old);
        last = newElement;
        if(isEmpty()){
            first = last;
        } else {
            old.next = newElement;
        }
        ++size;
    }

    /**
     * Метод удаляет последний элемент из списка, возвращая данные содержащиеся в удаляемом элементе
     * */
    public T removeLast(){

        if(isEmpty()){
            throw new NoSuchElementException();
        }
        Node old = last;
        last = old.previos;
        old.previos = null;
        --size;
        if (isEmpty()){
            first = null;
        } else {
            last.next = null;
        }
        return old.item;
    }

    /**
     * Метод возвращает данные содеждащиеся в элементе списка по индексу элемента
     * @param index - индекс элемента
     * */
    public T get (int index){

        if(index<0||index>size){
            throw new IndexOutOfBoundsException();
        }

        Node currentNode;
        boolean flag;
        int currentIndex;
        if(index<size/2){
            currentNode = first;
            flag = false;
            currentIndex = index;
        } else {
            currentNode = last;
            flag = true;
            currentIndex = size - (1 + index);
        }

        for (int i = 0; i < currentIndex; i++, currentNode = !flag ? currentNode.next : currentNode.previos) {;}
        return currentNode.item;
    }

    /**
     * Метод меняет данные содеждащиеся в элементе списка по индексу элемента
     * @param index - индекс элемента
     * */
    public void set (int index, @NonNull final T value){

        if(index<0||index>size){
            throw new IndexOutOfBoundsException();
        }

        Node currentNode;
        boolean flag;
        int currentIndex;
        if(index<size/2){
            currentNode = first;
            flag = false;
            currentIndex = index;
        } else {
            currentNode = last;
            flag = true;
            currentIndex = size - (1 + index);
        }

        for (int i = 0; i < currentIndex; i++, currentNode = !flag ? currentNode.next : currentNode.previos) {;}
        currentNode.item = value;
    }

    /**
     * Метод возвращает интекс элемента на основании совпадения данных искомых данных с данными содержащимися в элементе
     * @param value - данные на основании которых осуществляется сравнение
     * */
    public int indexOf(@NonNull final T value){

        if(isEmpty()){
            throw new NoSuchElementException();
        }

        Node currentNode = first;
        int currentIndex = 0;
        while(currentNode!=null && !currentNode.item.equals(value)){
            currentNode = currentNode.next;
            currentIndex++;
        }

        return currentNode != null ? currentIndex : -1;
    }

    /**
     * Метод возвращает boolean на основании совпадения данных искомых данных с данными содержащимися в элементе
     * @param value - данные на основании которых осуществляется сравнение
     * */
    public boolean contrains(@NonNull final T value){
        return indexOf(value) > -1;
    }

    /**
     * Метод удаляет элемент из произвольного положения в связанном списке
     * @param value - удаляемые данные
     * */
    public T remove(@NonNull final T value){

        Node current = first;
        while (current!=null && !current.item.equals(value)){
            current = current.next;
        }
        if(current==null){
            return null;
        }
        if(current==first){
            return removeFirst();
        } else if(current==last){
            return removeLast();
        } else {
            current.previos.next = current.next;
            current.next.previos = current.previos;
            current.next = null;
            current.previos = null;
        }
        --size;
        return current.item;
    }

    /**
     * Метод добавляет элемент в произвольное положение в связанном списке
     * @param index - индекс элемента перед которым будет добавлен новый элемент
     * @param value - новые данные
     * */
    public void add(int index, @NonNull final T value){

        if(index<0||index>size){
            throw new IndexOutOfBoundsException();
        }

        if(index==0){
            addFirst(value);
            return;
        }

        if(index==size){
            addLast(value);
            return;
        }

        int currentIndex = 0;
        Node current = first;

        while (currentIndex<index){
            current = current.next;
            currentIndex++;
        }

        Node newValue = new Node(value, current, current.previos);
        current.previos.next = newValue;
        current.previos = newValue;
        ++size;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node current = first;
        str.append("MyList(first -> last): ");
        while (current!=null){
            str.append(current.item.toString());
            if(current.next!=null) {str.append(", ");}
            current = current.next;
        }
        str.append(".");
        return str.toString();
    }
}
