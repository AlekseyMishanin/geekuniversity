package javacoreprofession.lesson6;
import javacoreprofession.lesson6.basic.Main;
import javacoreprofession.lesson6.basic.MyRuntimeException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MyTest {

    @Test
    public void isOkFindNumberInArray(){
        String str = "21374777";
        Assert.assertTrue(str.matches("^.*(?=.*1)(?=.*4).*$"));
    }

    @Test
    public void isNotOkFindNumberInArray(){
        String str = "2,3,7,7,7,7";
        boolean res = str.matches("^.*(?=.*1)(?=.*4).*$");
        Assert.assertEquals(res, false);
    }

    @Test
    public void isNotNullString(){
        final Integer[] array = { 2, 2, 3, 7};
        Assert.assertNotNull(Arrays.toString(array));
    }

    @Test
    public void isOkUpdateArray(){
        final Integer[] array = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        Integer value = 4;
        Integer[] result;
        final Integer[] current = {1, 7};
        result = Main.updateArray(value,array);
        Assert.assertTrue(Arrays.equals(current,result));
    }

    @Test
    public void isTimeoutOkUpdateArray(){
        final Integer[] array = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        Integer value = 4;
        Integer[] result;
        result = Main.updateArray(value,array);
    }

    @Test(expected = MyRuntimeException.class)
    public void isExceptionUpdateArray(){
        final Integer[] array = { 1, 2, 2, 3, 1, 7};
        Integer value = 4;
        Main.updateArray(value,array);
    }
}
