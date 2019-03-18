package algorithm.lesson5;

import algorithm.lesson5.base.TestExponentiation;
import algorithm.lesson5.base.TestHanoi;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestExponentiation.class,
        TestHanoi.class
})
public class TestAllMethod {
}
