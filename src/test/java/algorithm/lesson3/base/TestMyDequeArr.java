package algorithm.lesson3.base;

import algorithm.lesson3.MyDequeArr;
import org.junit.*;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import java.util.NoSuchElementException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMyDequeArr {

    private static MyDequeArr<Character> deq;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void setUp(){
        deq = new MyDequeArr<>(Character.class);
    }

    //Name Class
    @Test
    public void test01(){
        Class cl = deq.getClass();
        Assert.assertEquals(cl, MyDequeArr.class);
    }

    //IsEmpty
    @Test
    public void test02(){
        Assert.assertTrue(deq.isEmpty());
    }

    //Size equal Zero
    @Test
    public void test03(){
        Assert.assertEquals(deq.size(),0);
    }

    //Returnt type IsEmpty
    @Test
    public void test04(){
        Assert.assertTrue(Boolean.valueOf(deq.isEmpty()) instanceof Boolean);
    }

    //Return type Size
    @Test
    public void test05(){
        Assert.assertTrue(Integer.valueOf(deq.size()) instanceof Integer);
    }

    //InsertRight
    @Test
    public void test06(){
        deq.insertRight('a');
        Assert.assertFalse(deq.isEmpty());
        Assert.assertEquals(deq.size(),1);
        Assert.assertEquals(deq.peekRight(),Character.valueOf('a'));
    }

    //InsertLeft
    @Test
    public void test07(){
        deq.insertLeft('b');
        Assert.assertFalse(deq.isEmpty());
        Assert.assertEquals(deq.size(),2);
        Assert.assertEquals(deq.peekLeft(),Character.valueOf('b'));
    }

    //peekLeft
    @Test
    public void test08(){
        Assert.assertEquals(deq.peekLeft(),Character.valueOf('b'));
    }

    //peekLeft
    @Test
    public void test09(){
        Assert.assertEquals(deq.peekRight(),Character.valueOf('a'));
    }

    //removeLeft
    @Test
    public void test10(){
        Character temp = deq.removeLeft();
        Assert.assertEquals(temp,Character.valueOf('b'));
        Assert.assertFalse(deq.isEmpty());
        Assert.assertEquals(deq.size(),1);
    }

    //removeRight
    @Test
    public void test11(){
        Character temp = deq.removeRight();
        Assert.assertEquals(temp,Character.valueOf('a'));
        Assert.assertTrue(deq.isEmpty());
        Assert.assertEquals(deq.size(),0);
    }

    //removeRight exception
    @Test(expected = NoSuchElementException.class)
    public void test12() throws NoSuchElementException {
        Character temp = deq.removeRight();
    }

    //removeLeft exception
    @Test()
    public void test13() throws NoSuchElementException {
        thrown.expect(NoSuchElementException.class);
        Character temp = deq.removeLeft();
    }

    //peekRight exception
    @Test()
    public void test14() throws NoSuchElementException {
        thrown.expect(NoSuchElementException.class);
        Character temp = deq.peekRight();
    }

    //peekLeft exception
    @Test()
    public void test15() throws NoSuchElementException {
        thrown.expect(NoSuchElementException.class);
        Character temp = deq.peekLeft();
    }

    //toString
    @Test()
    public void test16() {
        String str = deq.toString();
        Assert.assertTrue(str.startsWith("["));
        Assert.assertTrue(str.endsWith("]"));
    }

    @BeforeClass
    public static void tearDown(){
        deq = null;
    }
}
