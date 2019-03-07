package algorithm.lesson2.base;

import algorithm.lesson3.Reverse;
import org.junit.Assert;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class TestReverseString {

    @DataPoints
    public static Object[][] testData = new Object[][]{
            {"12345", "54321"},
            {"abcdef", "fedcba"},
            {"g l 0 l", "l 0 l g"}
    };

    @Theory
    public void testNullData01(final Object... testData) {
        final boolean actual = testData[0] != null && testData[1] != null;
        Assert.assertTrue(actual);
        String str = Reverse.reverse1((CharSequence) testData[0]);
        Assert.assertEquals(str, (CharSequence)testData[1]);
    }

    @Theory
    public void testNullData02(final Object... testData) {
        final boolean actual = testData[0] != null && testData[1] != null;
        Assert.assertTrue(actual);
        String str = Reverse.reverse1((CharSequence) testData[0]);
        Assert.assertEquals(str, (CharSequence)testData[1]);
    }

    @Theory
    public void testNullData03(final Object... testData) {
        final boolean actual = testData[0] != null && testData[1] != null;
        Assert.assertTrue(actual);
        String str = Reverse.reverse1((CharSequence) testData[0]);
        Assert.assertEquals(str, (CharSequence)testData[1]);
    }
}
