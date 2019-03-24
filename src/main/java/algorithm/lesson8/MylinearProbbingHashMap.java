package algorithm.lesson8;

public class MylinearProbbingHashMap<T, V> {

    private int M = 97;
    private int size = 0;
    private Object[] keys = new Object[M];
    private Object[] values = new Object[M];

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private int hash(T key){
        return (key.hashCode() & 0x7fff_ffff) % M;
    }

    public V get(T key){

        if(key == null){
            throw new IllegalArgumentException();
        }

        for (int i = hash(key); keys[i] != null ; i = (i + 1) % M) {
            if(((T)keys[i]).equals(key)){
                return (V) values[i];
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
        if(size == M - 1){
            throw new IndexOutOfBoundsException();
        }
        int i;
        for (i = hash(key); keys[i] != null ; i = (i + 1) % M) {
            if(((T)keys[i]).equals(key)){
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        size++;
    }
}
