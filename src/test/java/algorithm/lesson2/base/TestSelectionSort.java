package algorithm.lesson2.base;

import algorithm.lesson2.ArrayListDemo;
import org.junit.Assert;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;


@RunWith(Theories.class)
public class TestSelectionSort {

    @DataPoint
    public static Integer[] testData = new Integer[]{
            1,3,5,7,9,0,8,6,4,2
    };

    private final Integer[] sortRez = new Integer[]{0,1,2,3,4,5,6,7,8,9};

    @Theory
    public void testSelectionSort(final Integer... testData) {
        final ArrayListDemo<Integer> arr = new ArrayListDemo<>(Integer.class);
        for (int i = 0; i < testData.length; i++) {
            arr.add(testData[i]);
        }
        arr.selectionSort(Integer::compareTo);
        for (int i = 0; i < sortRez.length; i++) {
            Assert.assertEquals(arr.get(i), (Integer) sortRez[i]);
        }
    }
}