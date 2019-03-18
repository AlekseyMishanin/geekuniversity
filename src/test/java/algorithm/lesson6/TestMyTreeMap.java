package algorithm.lesson6;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class TestMyTreeMap extends Assert {

    private static MyTreeMap<Character, Integer> tree;

    private static Character character = 'W';

    @DataPoints
    public static  Object[][] testData = new Object[][]{

            {"abcdef", true},
            {"abcddd", false},
            {"awcdyf", true}
    };

    @BeforeClass
    public static void setUp(){

        tree = new MyTreeMap<>();
    }

    public void clear(){

        tree = new MyTreeMap<>();
    }

    @Test
    public void testIsEmpty(){

        clear();
        assertTrue(tree.isEmpty());
    }

    @Test
    public void testSizeIsZero(){

        clear();
        assertEquals(tree.size(),0);
    }

    @Test
    public void testHeightIsZero(){

        clear();
        assertEquals(tree.height(),0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void  testPutKeyIsNull(){

        clear();
        tree.put(null,1);
    }

    @Theory
    public void testPut(final Object ... testData){
        clear();
        String str = (String)testData[0];
        for (char ch:
                str.toCharArray()) {
            tree.put(ch, 1);
        }
        assertFalse(tree.isEmpty());
        assertEquals(tree.size()==str.length(),testData[1]);
    }

    @Theory
    public void testRemote(final Object ... testData){
        clear();
        String str = (String)testData[0];
        for (char ch:
                str.toCharArray()) {
            tree.put(ch, 1);
        }
        assertFalse(tree.isEmpty());
        int size = tree.size();
        tree.remove(character);
        assertTrue(size==tree.size());
        tree.remove(str.charAt(1));
        assertTrue(size-1==tree.size());

    }

    @Test(expected = IllegalArgumentException.class)
    public void  testGetKeyIsNull(){

        clear();
        tree.get(null);
    }

    @Theory
    public void testGet(final Object ... testData){
        clear();
        String str = (String)testData[0];
        for (char ch:
                str.toCharArray()) {
            tree.put(ch, 1);
        }
        assertFalse(tree.isEmpty());
        assertTrue(tree.get(str.charAt(1))==1);
    }

    @AfterClass
    public static void tearDown(){

        tree = null;
    }
}
