package algorithm.lesson2.base;

import algorithm.lesson2.ArrayListDemo;
import org.junit.*;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMyArrayList {

    public static ArrayListDemo<Character> list;
    private final Character[] testdata = new Character[]{'a','b','c','d','e','f','g'};
    private final Character charNotInList = 'o';
    private final Character charInList = 'd';

    @BeforeClass
    public static void setUp(){
        list = new ArrayListDemo<>(Character.class);
    }

    //Name Class
    @Test
    public void test01(){
        Class cl = list.getClass();
        Assert.assertEquals(cl, ArrayListDemo.class);
    }

    //Length
    @Test
    public void test02(){
        Assert.assertEquals(list.length(),1);
    }

    //Size equal Zero
    @Test
    public void test03(){
        Assert.assertEquals(list.size(),0);
    }

    //Return type size() and lenght()
    @Test
    public void test04(){
        final boolean actualLength = Integer.valueOf(list.length()) instanceof Integer;
        final boolean actualSize = Integer.valueOf(list.size()) instanceof Integer;
        Assert.assertEquals(actualLength, actualSize);
    }

    //exception from get()
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void test05() throws ArrayIndexOutOfBoundsException{
        list.get(-1);
        list.get(1);
    }

    //exception from set()
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void test06() throws ArrayIndexOutOfBoundsException{
        list.set(1, 'a');
    }

    //add() and contrains()
    @Test()
    public void test07(){
        for (Character value: testdata) {
            list.add(value);
        }
        for (Character value: testdata) {
            Assert.assertTrue(list.contains(value));
        }
        Assert.assertEquals(testdata.length, list.size());
    }

    //get() and indexOf()
    @Test()
    public void test08(){
        for (int i = 0; i < testdata.length; i++) {
            Assert.assertEquals(testdata[i],list.get(i));
            Assert.assertEquals(i,list.indexOf(testdata[i]));
        }
        Assert.assertEquals(-1, list.indexOf(charNotInList));
    }

    //remove()
    @Test()
    public void test09(){
        Assert.assertTrue(list.remove(charInList));
        Assert.assertFalse(list.remove(charNotInList));
    }

    //toString()
    @Test()
    public void test10(){
        ArrayListDemo<Character> local = new ArrayListDemo<>(Character.class);
        Assert.assertEquals("[]", local.toString());
        Assert.assertTrue(list.toString().startsWith("["));
        Assert.assertTrue(list.toString().endsWith("]"));
    }

    @AfterClass
    public static void tearDown(){
        list = null;
    }
}
