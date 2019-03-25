package algorithm.lesson8;


/**
 * Хеш-таблица, реализующая механизм двойного хеширования
 * */
public class MyDoubleHashingHashMap<T, V> {

    private int capacity;                                   //размер массива
    private int size = 0;                                   //кол-во элементов
    private Object[] items;                                 //массив объектов
    private Node nullItem;                                  //специальный объект для пометки удаленных объектов
    private static final double LOADFACTORMAX = 0.75;       //коэффициент максимальной загрузки
    private static final double LOADFACTORMIN = 0.20;       //коэффициент минимальной загрузки

    /**
     * Конструктор с параметрами
     * @param capacity - заданный размер массива объектов
     * */
    public MyDoubleHashingHashMap(int capacity){

        //проверяем является ли capacity простым числом
        if(isPrimeNumber(capacity)){
            this.capacity = capacity;
        } else {
            this.capacity = primeNumber(capacity);
        }

        size = 0;
        items = new Object[this.capacity];             //создаем массив объектов
        nullItem = new Node();                         //создаем помеченный объект
    }

    /**
     * Внутренний класс
     * */
    private class Node{

        T key;                      //ключ
        V value;                    //значение
        private boolean isEmpty;    //признак того, что объект содержит некорректные данные

        public Node(T key, V value) {
            this.key = key;
            this.value = value;
            this.isEmpty = false;
        }

        /**
         * Конструктор по умолчанию используется для создания объекта оболочки для замены удаляемого объекта,
         * чтобы избежать необходимости перемещения многих объектов.
         * */
        public Node() {
            key = null;
            value = null;
            this.isEmpty = true;
        }

        public boolean isEmpty() {
            return isEmpty;
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

    /**
     * Метод повторного хеширования
     * */
    private int doubleHash(T key){
        return 7 - key.hashCode() % 7;
    }

    public V get(T key){

        if(key == null){
            throw new IllegalArgumentException();
        }

        for (int i = hash(key); items[i] != null; i = (i + doubleHash(key)) % capacity) {
            if(!((Node)items[i]).isEmpty() && ((T)((Node)items[i]).key).equals(key)){
                return ((Node)items[i]).value;
            }
        }
        return null;
    }

    public boolean contains(T key){
        return get(key) != null;
    }

    public void put(T key, V value){

        if (key == null){
            throw new IllegalArgumentException();
        }

        if(size*1.0/capacity >= LOADFACTORMAX){                                     //если коэффициент заполнения хеш-таблицы выше максимального значение
            resize(capacity * 2);                                           //увеличиваем размер хеш-таблицы
        }

        if(size == capacity - 1){
            throw new IndexOutOfBoundsException();
        }

        int i;
        for (i = hash(key); items[i] != null; i = (i + doubleHash(key)) % capacity) {
            if(!((Node)items[i]).isEmpty() && (((Node)items[i]).key).equals(key)){
                ((Node)items[i]).value = value;
                return;
            }
        }
        items[i] = new Node(key,value);
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

        for (int i = hash(key); items[i] != null; i = (i + doubleHash(key)) % capacity) {

            if(!((Node)items[i]).isEmpty() && ((T)((Node)items[i]).key).equals(key)){   //если объект не помечен как удаленный и если ключи совпадают то
                items[i] = nullItem;                                                    //помечаем объект как удаленный
                size--;                                                                 //уменьшаем кол-во элементов
                break;                                                                  //выходим из цикла
            }
        }

        if(size*1.0/capacity <= LOADFACTORMIN && size > 0){                             //если коэффициент заполнения хеш-таблицы ниже минимального значение
            resize(capacity / 2);                                               //уменьшаем размер хеш-таблицы
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
        Object[] temp = items;                  //сохраняем ссылку на старный массив объектов
        this.capacity = primeNumber(capacity);  //capacity действительно простое число?
        items = new Object[this.capacity];      //создаем массив объектов новой размерности
        size = 0;                               //обновляем размер
        for (int i = 0; i < temp.length; i++) { //пересчитываем хеш всех не пустых элементов
            Node x = (Node)temp[i];
            if(x != null && !x.isEmpty()){
                put(x.key, x.value);
            }
        }
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();
        str.append("capacity: " + capacity + '\n');
        str.append("size: " + size + '\n');
        str.append("items:\n");
        for (int i = 0; i < items.length; i++) {
            Node x = (Node)items[i];
            if(items[i] != null && !((Node)items[i]).isEmpty()){
                str.append("key: " + ((Node)items[i]).key + ", value: " + ((Node)items[i]).value + '\n');
            }
        }
        return str.toString();
    }
}
