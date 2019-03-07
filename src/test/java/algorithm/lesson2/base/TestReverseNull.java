package algorithm.lesson2.base;

import algorithm.lesson3.Reverse;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class TestReverseNull {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @DataPoint public static Object[] nullData = {null, true};

    @Theory
    public void testNullData01(final Object... testData) throws NullPointerException{
        thrown.expect(NullPointerException.class);
        final boolean actual = testData[0] == null;
        Assert.assertEquals(testData[1], actual);
        Reverse.reverse1((CharSequence) testData[0]);
    }

    @Theory
    public void testNullData02(final Object... testData) throws NullPointerException{
        thrown.expect(NullPointerException.class);
        final boolean actual = testData[0] == null;
        Assert.assertEquals(testData[1], actual);
        Reverse.reverse2((CharSequence) testData[0]);
    }

    @Theory
    public void testNullData03(final Object... testData) throws NullPointerException{
        thrown.expect(NullPointerException.class);
        final boolean actual = testData[0] == null;
        Assert.assertEquals(testData[1], actual);
        Reverse.reverse3((CharSequence) testData[0]);
    }
}
