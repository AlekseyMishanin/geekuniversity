package algorithm.lesson5.base;

import algorithm.lesson5.HomeWork;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

public class TestHanoi extends Assert {

    private static HomeWork homeWork;
    private static LinkedList<Integer> rezult;
    private static LinkedList<Integer> A;
    private static LinkedList<Integer> B;
    private static LinkedList<Integer> C;

    public void clear(){
        A = new LinkedList<>();
        B = new LinkedList<>();
        C = new LinkedList<>();
    }

    @BeforeClass
    public static void setUp(){
        homeWork = new HomeWork();
        rezult = new LinkedList<>();
        rezult.addAll(Arrays.asList(new Integer[]{1,2,3,4,5}));
    }

    @Test
    public void testTowerOfHanoiCycle(){
        clear();
        assertTrue(rezult.containsAll(A));
        assertTrue(B.isEmpty());
        assertTrue(C.isEmpty());
        homeWork.towerOfHanoiCycle(5,A,B,C);
        assertTrue(A.isEmpty());
        assertTrue(B.isEmpty());
        assertTrue(rezult.containsAll(C));
    }

    @Test
    public void testTowerOfHanoiRecursio(){
        clear();
        assertTrue(rezult.containsAll(A));
        assertTrue(B.isEmpty());
        assertTrue(C.isEmpty());
        homeWork.towerOfHanoiRecursio(5,A,B,C);
        assertTrue(A.isEmpty());
        assertTrue(B.isEmpty());
        assertTrue(rezult.containsAll(C));
    }

    @Test(expected = ArithmeticException.class)
    public void testTowerOfHanoiCycleException(){
        clear();
        homeWork.towerOfHanoiCycle(-1,A,B,C);
    }

    @Test(expected = ArithmeticException.class)
    public void testTowerOfHanoiException(){
        clear();
        homeWork.towerOfHanoiRecursio(-5,A,B,C);
    }
}
