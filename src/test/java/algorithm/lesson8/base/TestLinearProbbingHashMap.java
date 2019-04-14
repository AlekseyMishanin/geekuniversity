package algorithm.lesson8.base;

import algorithm.lesson8.MylinearProbbingHashMap;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestLinearProbbingHashMap extends Assert {


    private static MylinearProbbingHashMap map;
    private static int capacity;
    private static final double LOADFACTORMAX = 0.75;       //коэффициент максимальной загрузки
    private static final double LOADFACTORMIN = 0.20;       //коэффициент минимальной загрузки

    //хеш-функция
    private int hash(Integer key){
        return (key.hashCode() & 0x7fff_ffff) % capacity;
    }

    private class Node{

        Integer key;                        //ключ
        Integer value;                      //значение
        private boolean isEmpty;            //признак того, что объект содержит некорректные данные

        public Node(Integer key, Integer value) {
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

    @BeforeClass
    public static void setUp(){

        capacity = 5;
        map = new MylinearProbbingHashMap(capacity);
    }

    //test isPrimeNumber()
    @Test
    public void test01(){

        int[] arr = new int[]{3,5,7,11,13,17,19,23};
        for (int num:
        arr) {
            boolean flag = true;
            for (int i = 2; i*i <= num ; i += (i % 2 == 0) ? 1 : 2 ) {
                if(num % i == 0){
                    flag = !flag;
                    break;
                }
            }
            assertTrue(flag);
        }
    }

    //тестирование хеш-таблицы на пустоту при условии отсутствия элементов в таблице
    @Test
    public void test02(){
        assertTrue(map.isEmpty());
    }

    //тестирование размера хеш-таблицы при условии отсутствия элементов в таблице
    @Test
    public void test03(){
        assertTrue(map.size() == 0);
    }

    //тестирование хеш-функции
    @Test
    public void test04(){

        Integer[] array = new Integer[]{1,2,3,4};
        int capacity = 23;
        for (int i = 0; i < array.length; i++) {
            int a1 = hash(array[i]);
            int a2 = hash(array[i]);
            assertEquals(a1,a2);
        }

        int a1 = hash(array[0]);
        int a2 = hash(array[1]);
        assertNotEquals(a1,a2);
    }

    //тестирование механизма линейного пробирования
    @Test
    public void test05(){

        Node[] data = new Node[capacity];
        for (int j = 0; j < capacity; j++) {
            int i;
            for (i = hash(j); data[i] != null; i = (i + 1) % capacity) {
                if(!((Node)data[i]).isEmpty()
                        && (((Node)data[i]).key).equals(j)){
                    ((Node)data[i]).value = 2;
                    return;
                }
            }
            data[i] = new Node(j,1);
        }

        boolean flag = false;

        for (Node nd:
        data) {
            assertNotNull(nd);
            if(nd.value == 2) flag = true;
        }
        assertTrue(!flag);
    }

    //тестирование метода put c ключем равным null
    @Test(expected = IllegalArgumentException.class)
    public void test06(){

        map.put(null,1);
    }

    //тестирование метода put
    @Test()
    public void test07(){

        int tempCapacity = capacity;

        for (int i = 0; i < 9; i++) {
            if(map.size()*1.0/tempCapacity >= LOADFACTORMAX){
                tempCapacity *= 2;
            }
            map.put(i,i);
            assertEquals(map.size(),i+1);
        }
        assertNotEquals(tempCapacity,capacity);
    }

    //тестирование метода get c ключем равным null
    @Test(expected = IllegalArgumentException.class)
    public void test08(){

        map.get(null);
    }

    //тестирование метода get
    @Test()
    public void test09(){

        for (int i = 0; i < 9; i++) {
            assertEquals(map.get(i),i);
        }
    }

    //тестирование метода remove c ключем равным null
    @Test(expected = IllegalArgumentException.class)
    public void test10(){

        map.remove(null);
    }

    //тестирование метода remove
    @Test()
    public void test11(){
        
        for (int i = 0; i < 9; i++) {

            int sz = map.size();
            map.remove(i);
            assertEquals(map.size(),sz - 1);
        }
    }

    @AfterClass
    public static void tearDown(){

        map = null;
    }
}
