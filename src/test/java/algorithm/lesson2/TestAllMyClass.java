package algorithm.lesson2;

import algorithm.lesson2.base.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestMyArrayList.class,
        TestSelectionSort.class,
        TestInsertionSort.class,
        TestShellSort.class,
        TestQuickSort.class
})
public class TestAllMyClass {
}
