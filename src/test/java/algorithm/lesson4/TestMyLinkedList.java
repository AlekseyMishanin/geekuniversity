package algorithm.lesson4;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.NoSuchElementException;

public class TestMyLinkedList extends Assert {

    private static MyLinkedList<Character> linkedList;

    @BeforeClass
    public static void setUp(){
        linkedList = new MyLinkedList<>();
    }

    private void clear(){
        linkedList = new MyLinkedList<>();
    }

    public void addDataToList(int count){
        for (int i = 0; i < count; i++) {
            linkedList.addFirst(String.valueOf(i).charAt(0));
        }
    }

    @Test
    public void testSizeZero(){
        clear();
        assertTrue(linkedList.size()==0);
    }

    @Test
    public void testSizeNotZero(){
        clear();
        addDataToList(15);
        assertTrue(linkedList.size()==15);
    }

    @Test
    public void testIsEmpty(){
        clear();
        assertTrue(linkedList.isEmpty());
    }

    @Test
    public void testIsNotEmpty(){
        clear();
        addDataToList(15);
        assertFalse(linkedList.isEmpty());
    }

    @Test
    public void testGetFirst(){
        clear();
        addDataToList(10);
        assertEquals(linkedList.getFirst(),(Object)'9');
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetFirstException(){
        clear();
        linkedList.getFirst();
    }

    @Test(expected = NullPointerException.class)
    public void testAddFirstException(){
        clear();
        linkedList.addFirst(null);
    }

    @Test()
    public void testAddFirst(){
        clear();
        linkedList.addFirst('1');
        linkedList.addFirst('2');
        linkedList.addFirst('3');
        assertFalse(linkedList.isEmpty());
        assertTrue(linkedList.size()==3);
        assertEquals((Object)'3',linkedList.getFirst());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFirstException(){
        clear();
        linkedList.removeFirst();
    }

    @Test
    public void testRemoveFirst(){
        clear();
        addDataToList(5);
        assertEquals(linkedList.getFirst(),(Object)'4');
        assertTrue(linkedList.size()==5);
        Character value = linkedList.removeFirst();
        assertTrue(linkedList.size()==4);
        assertEquals(linkedList.getFirst(),(Object)'3');
        assertEquals(value, (Object)'4');
        linkedList.removeFirst();
        linkedList.removeFirst();
        linkedList.removeFirst();
        linkedList.removeFirst();
        assertTrue(linkedList.isEmpty());
    }

    @Test
    public void testGetLast(){
        clear();
        addDataToList(10);
        assertEquals(linkedList.getLast(),(Object)'0');
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetLastException(){
        clear();
        linkedList.getLast();
    }

    @Test(expected = NullPointerException.class)
    public void testAddLastException(){
        clear();
        linkedList.addLast(null);
    }

    @Test()
    public void testAddLast(){
        clear();
        linkedList.addLast('1');
        linkedList.addLast('2');
        linkedList.addLast('3');
        assertFalse(linkedList.isEmpty());
        assertTrue(linkedList.size()==3);
        assertEquals((Object)'1',linkedList.getFirst());
        assertEquals((Object)'3',linkedList.getLast());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveLastException(){
        clear();
        linkedList.removeLast();
    }

    @Test
    public void testRemoveLast(){
        clear();
        addDataToList(5);
        assertEquals(linkedList.getFirst(),(Object)'4');
        assertTrue(linkedList.size()==5);
        Character value = linkedList.removeLast();
        assertTrue(linkedList.size()==4);
        assertEquals(linkedList.getLast(),(Object)'1');
        assertEquals(value, (Object)'0');
        linkedList.removeFirst();
        linkedList.removeFirst();
        linkedList.removeFirst();
        linkedList.removeFirst();
        assertTrue(linkedList.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetException(){
        clear();
        linkedList.get(-1);
    }

    @Test
    public void testGetMoreMedium(){
        clear();
        addDataToList(10);
        assertEquals(linkedList.get(9),(Object)'0');
        assertEquals(linkedList.get(8),(Object)'1');
        assertEquals(linkedList.get(7),(Object)'2');
        assertEquals(linkedList.get(6),(Object)'3');
    }

    @Test
    public void testGetLessMedium(){
        clear();
        addDataToList(10);
        assertEquals(linkedList.get(0),(Object)'9');
        assertEquals(linkedList.get(1),(Object)'8');
        assertEquals(linkedList.get(2),(Object)'7');
        assertEquals(linkedList.get(3),(Object)'6');
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetException1(){
        clear();
        linkedList.set(-1, 'a');
    }

    @Test(expected = NullPointerException.class)
    public void testSetException2(){
        clear();
        addDataToList(10);
        linkedList.set(1, null);
    }

    @Test
    public void testSetMoreMedium(){
        clear();
        addDataToList(10);
        linkedList.set(6,'L');
        linkedList.set(7,'D');
        assertEquals(linkedList.get(6),(Object)'L');
        assertEquals(linkedList.get(7),(Object)'D');
    }

    @Test
    public void testSetLessMedium(){
        clear();
        addDataToList(10);
        linkedList.set(1,'L');
        linkedList.set(2,'D');
        assertEquals(linkedList.get(1),(Object)'L');
        assertEquals(linkedList.get(2),(Object)'D');
    }

    @Test(expected = NullPointerException.class)
    public void testIndexOfException(){
        clear();
        linkedList.indexOf(null);
    }

    @Test
    public void testIndexOf(){
        clear();
        addDataToList(10);
        int index = linkedList.indexOf('0');
        assertTrue(index==9);
    }

    @Test
    public void testContrainsValueEquals(){
        clear();
        addDataToList(10);
        assertTrue(linkedList.contrains('5'));
    }

    @Test
    public void testContrainsValueNotEquals(){
        clear();
        addDataToList(10);
        assertFalse(linkedList.contrains('L'));
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveException(){
        clear();
        addDataToList(10);
        linkedList.remove(null);
    }

    @Test
    public void testRemove(){
        clear();
        addDataToList(10);
        assertTrue(linkedList.size()==10);

        Character value = linkedList.remove('0');
        assertTrue(linkedList.size()==9);
        assertTrue(value.equals('0'));
        assertEquals((Object)'1', linkedList.getLast());

        value = linkedList.remove('9');
        assertTrue(linkedList.size()==8);
        assertTrue(value.equals('9'));
        assertEquals((Object)'8', linkedList.getFirst());

        value = linkedList.remove('4');
        assertTrue(linkedList.size()==7);
        assertTrue(value.equals('4'));
        assertEquals((Object)'5', linkedList.get(3));

    }


    @Test(expected = NullPointerException.class)
    public void testAddException(){
        clear();
        addDataToList(10);
        linkedList.add(1,null);
    }

    @Test
    public void testAdd(){
        clear();
        addDataToList(10);
        assertTrue(linkedList.size()==10);

        linkedList.add(0,'L');
        assertTrue(linkedList.size()==11);
        assertEquals((Object)'L', linkedList.getFirst());

        linkedList.add(11,'B');
        assertTrue(linkedList.size()==12);
        assertEquals((Object)'B', linkedList.getLast());

        linkedList.add(5,'C');
        assertTrue(linkedList.size()==13);
        assertEquals((Object)'C', linkedList.get(5));
    }

    @AfterClass
    public static void tearDown(){
        linkedList = null;
    }
}
