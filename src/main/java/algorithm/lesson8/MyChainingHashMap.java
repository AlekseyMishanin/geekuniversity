package algorithm.lesson8;

/**
 * Хеш-таблица, реализующая метод цепочек
 * */
public class MyChainingHashMap<T, V> {

    private int capacity;                                   //размер массива
    private int size;                                       //кол-во элементов
    private Object[] st;                                    //массив списков элементов
    private static final double LOADFACTORMAX = 0.75;       //коэффициент максимальной загрузки
    private static final double LOADFACTORMIN = 0.20;       //коэффициент минимальной загрузки

    public MyChainingHashMap(int capacity){

        if(isPrimeNumber(capacity)){
            this.capacity = capacity;
        } else {
            this.capacity = primeNumber(capacity);
        }

        size = 0;
        st = new Object[this.capacity];
    }

    /**
     * Внутренний класс
     * */
    private class Node{

        T key;          //ключ
        V value;        //значение
        Node next;      //ссылка на следующий узел
        Node previos;   //ссылка на предыдущий узел

        public Node(T key, V value, Node next, Node previos) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.previos = previos;
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private int hash(T key){
        return (key.hashCode() & 0x7fff_ffff) % capacity;
    }

    public V get(T key){

        if (key == null){
            throw new IllegalArgumentException();
        }

        int i = hash(key);
        Node x = (Node) st[i];
        while (x != null){
            if(key.equals(x.key)){
                return x.value;
            }
            x = x.next;
        }
        return  null;
    }

    public boolean contains(T key){
        return get(key) != null;
    }

    public void put (T key, V value){

        if (key == null){
            throw new IllegalArgumentException();
        }

        if(size*1.0/capacity >= LOADFACTORMAX){             //если коэффициент заполнения хеш-таблицы выше максимального значение
            resize(capacity * 2);                   //увеличиваем размер хеш-таблицы
        }

        int i = hash(key);
        Node x = (Node) st[i];
        while (x != null){
            if(key.equals(x.key)){
                x.value = value;
                return;
            }
            x = x.next;
        }
        Node oldSt = (Node) st[i];                          //ссылка старый первый узел
        st[i] = new Node(key, value, oldSt, null);  //создаем ссылку на новый первый узел
        if(oldSt != null) oldSt.previos = (Node)st[i];      //обновляем в старом первом узле ссылку на предыдущий узел
        size++;
    }

    /**
     * Метод удаления элемента по ключу.
     * @param key - ключ удаляемого элемента
     * */
    public void remove (T key){

        if (key == null){
            throw new IllegalArgumentException();
        }

        int i = hash(key);
        Node x = (Node) st[i];
        while (x != null){
            if(key.equals(x.key)){                              //если элемент найден
                if(x.previos != null) {                         //и если предыдущий элемент существует
                    x.previos.next = x.next;                    //обновляем в предыдущем элементе ссылку next
                }
                if(x.next != null) {                            //если следующий элемент существует
                    x.next.previos = x.previos;                 //обновляем в следующем элементе ссылку previos
                }
                if(x.next == null && x.previos == null) {       //если в списке единственный элемент
                    st[i]=null;                                 //удалить ссылку на список
                }
                x = null;                                       //удаляем узел с ключем == key
                size--;                                         //уменьшаем кол-во элементов на 1
                break;
            }
            x = x.next;
        }

        if(size*1.0/capacity <= LOADFACTORMIN && size > 0){     //если коэффициент заполнения хеш-таблицы ниже минимального значение
            resize(capacity / 2);                       //уменьшаем размер хеш-таблицы
        }
    }

    /**
     * Метод ищет ближайщее наименьшее простое число
     * @param start - начальная позиция для поиска простого числа
     * */
    private int primeNumber(int start){

        int step = start + 1;
        while (true){
            if(isPrimeNumber(step)){
                return step;
            }
            step++;
        }
    }

    /**
     * Метод проверяет является ли переданное число простым
     * @param value - проверяемое число
     * */
    private boolean isPrimeNumber(int value){

        //i += (i % 2 == 0) ? 1 : 2  --исключаем из всех итераций четные числа, т.к. достаточно начальной четной 2
        for (int i = 2; i*i <= value ; i += (i % 2 == 0) ? 1 : 2 ) {
            if(value % i == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * Метод перехеширования.
     * @param capacity - новый размер массива
     * */
    private void resize(int capacity){

        if (capacity <= 0 ){
            throw new IllegalArgumentException();
        }
        Object[] temp = st;                     //сохраняем ссылку на старый массив
        this.capacity = primeNumber(capacity);  //capacity действительно простое число?
        st = new Object[this.capacity];         //создаем новый массив
        size = 0;                               //обновляем размер
        for (int i = 0; i < temp.length; i++) {
            Node x = (Node) temp[i];                //получаем ссылку на первый узел списка элемента массива
            while (x != null){                      //пересчитываем хеш всех узлов списка
                put(x.key, x.value);
                x = x.next;
            }
        }
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();
        str.append("capacity: " + capacity + '\n');
        str.append("size: " + size + '\n');
        str.append("items:\n");
        for (int i = 0; i < st.length; i++) {
            Node x = (Node)st[i];
            while (x != null){                      //пересчитываем хеш всех узлов списка
                str.append("key: " + x.key + ", value: " + x.value + '\n');
                x = x.next;
            }
        }
        return str.toString();
    }
}
