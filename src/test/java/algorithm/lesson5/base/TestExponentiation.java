package algorithm.lesson5.base;

import algorithm.lesson5.HomeWork;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestExponentiation extends Assert {

    //используем аннотацию Mock для создания mock объекта
    @Mock
    HomeWork homeWork = new HomeWork();

   @Test
    public void testExponentiationCycle(){

       //определяем поведение объекта для операции возведения в степень (через цикл)
        when(homeWork.exponentiationCycle(2,4)).thenReturn((long)16);
        when(homeWork.exponentiationCycle(2,5)).thenReturn((long)32);
        when(homeWork.exponentiationCycle(2,6)).thenReturn((long)64);

        //проверяем действие возведения в степень
       assertEquals(homeWork.exponentiationCycle(2,4),16);
       assertEquals(homeWork.exponentiationCycle(2,4),16);
       assertEquals(homeWork.exponentiationCycle(2,4),16);

       assertEquals(homeWork.exponentiationCycle(2,5),32);

       assertEquals(homeWork.exponentiationCycle(2,6),64);

       //проверяем выполнение действия
       verify(homeWork, atLeast(3)).exponentiationCycle(2,4);   //был ли вызван метод не меньше 3-х раз
       verify(homeWork, atLeastOnce()).exponentiationCycle(2,5);                     //был ли вызван метод хотя бы 1 раз
       verify(homeWork, atLeastOnce()).exponentiationCycle(2,6);                     //был ли вызван метод хотя бы 1 раз

       verify(homeWork, never()).exponentiationCycle(2,0);                           //метод не был вызван ни разу

       //определяем поведение с использованием doReturn
       doReturn((long)8).when(homeWork).exponentiationCycle(2,3);

       //проверяем действие
       assertEquals(homeWork.exponentiationCycle(2,3),8);
       verify(homeWork).exponentiationCycle(2,3);
    }

    @Test(expected = ArithmeticException.class)
    public void testExponentiationCycleException() throws ArithmeticException{

        ArithmeticException exp = new ArithmeticException();
        int countErrors = 0;

       doThrow(exp).when(homeWork).exponentiationCycle(2,-1);
       doThrow(exp).when(homeWork).exponentiationCycle(2,-2);
       doThrow(exp).when(homeWork).exponentiationCycle(2,-3);

       try{
            assertEquals(homeWork.exponentiationCycle(2,-1), 0);
       } catch (ArithmeticException e){++countErrors;}
       try{
            assertEquals(homeWork.exponentiationCycle(2,-1), 0);
       } catch (ArithmeticException e){++countErrors;}
       try{
            assertEquals(homeWork.exponentiationCycle(2,-2), 0);
       } catch (ArithmeticException e){++countErrors;}
       try {
            assertEquals(homeWork.exponentiationCycle(2,-3), 0);
       } catch (ArithmeticException e){++countErrors;}

        verify(homeWork, times(2)).exponentiationCycle(2,-1);
        verify(homeWork, atLeastOnce()).exponentiationCycle(2,-2);
        verify(homeWork, atLeastOnce()).exponentiationCycle(2,-3);

        if(countErrors==4) throw new ArithmeticException();
    }

    @Test
    public void testExponentiationRecursioSlow(){

        //определяем поведение объекта для операции возведения в степень (через цикл)
        when(homeWork.exponentiationRecursioSlow(2,4)).thenReturn((long)16);
        when(homeWork.exponentiationRecursioSlow(2,5)).thenReturn((long)32);
        when(homeWork.exponentiationRecursioSlow(2,6)).thenReturn((long)64);

        //проверяем действие возведения в степень
        assertEquals(homeWork.exponentiationRecursioSlow(2,4),16);
        assertEquals(homeWork.exponentiationRecursioSlow(2,4),16);
        assertEquals(homeWork.exponentiationRecursioSlow(2,4),16);

        assertEquals(homeWork.exponentiationRecursioSlow(2,5),32);

        assertEquals(homeWork.exponentiationRecursioSlow(2,6),64);

        //проверяем выполнение действия
        verify(homeWork, atLeast(3)).exponentiationRecursioSlow(2,4);   //был ли вызван метод не меньше 3-х раз
        verify(homeWork, atLeastOnce()).exponentiationRecursioSlow(2,5);                     //был ли вызван метод хотя бы 1 раз
        verify(homeWork, atLeastOnce()).exponentiationRecursioSlow(2,6);                     //был ли вызван метод хотя бы 1 раз

        verify(homeWork, never()).exponentiationRecursioSlow(2,0);                           //метод не был вызван ни разу

        //определяем поведение с использованием doReturn
        doReturn((long)8).when(homeWork).exponentiationRecursioSlow(2,3);

        //проверяем действие
        assertEquals(homeWork.exponentiationRecursioSlow(2,3),8);
        verify(homeWork).exponentiationRecursioSlow(2,3);
    }

    @Test(expected = ArithmeticException.class)
    public void testExponentiationRecursioSlowException() throws ArithmeticException{

        ArithmeticException exp = new ArithmeticException();
        int countErrors = 0;

        doThrow(exp).when(homeWork).exponentiationRecursioSlow(2,-1);
        doThrow(exp).when(homeWork).exponentiationRecursioSlow(2,-2);
        doThrow(exp).when(homeWork).exponentiationRecursioSlow(2,-3);

        try{
            assertEquals(homeWork.exponentiationRecursioSlow(2,-1), 0);
        } catch (ArithmeticException e){++countErrors;}
        try{
            assertEquals(homeWork.exponentiationRecursioSlow(2,-1), 0);
        } catch (ArithmeticException e){++countErrors;}
        try{
            assertEquals(homeWork.exponentiationRecursioSlow(2,-2), 0);
        } catch (ArithmeticException e){++countErrors;}
        try {
            assertEquals(homeWork.exponentiationRecursioSlow(2,-3), 0);
        } catch (ArithmeticException e){++countErrors;}

        verify(homeWork, times(2)).exponentiationRecursioSlow(2,-1);
        verify(homeWork, atLeastOnce()).exponentiationRecursioSlow(2,-2);
        verify(homeWork, atLeastOnce()).exponentiationRecursioSlow(2,-3);

        if(countErrors==4) throw new ArithmeticException();
    }

    @Test
    public void testExponentiationRecursioFast(){

        //определяем поведение объекта для операции возведения в степень (через цикл)
        when(homeWork.exponentiationRecursioFast(2,4)).thenReturn((long)16);
        when(homeWork.exponentiationRecursioFast(2,5)).thenReturn((long)32);
        when(homeWork.exponentiationRecursioFast(2,6)).thenReturn((long)64);

        //проверяем действие возведения в степень
        assertEquals(homeWork.exponentiationRecursioFast(2,4),16);
        assertEquals(homeWork.exponentiationRecursioFast(2,4),16);
        assertEquals(homeWork.exponentiationRecursioFast(2,4),16);

        assertEquals(homeWork.exponentiationRecursioFast(2,5),32);

        assertEquals(homeWork.exponentiationRecursioFast(2,6),64);

        //проверяем выполнение действия
        verify(homeWork, atLeast(3)).exponentiationRecursioFast(2,4);   //был ли вызван метод не меньше 3-х раз
        verify(homeWork, atLeastOnce()).exponentiationRecursioFast(2,5);                     //был ли вызван метод хотя бы 1 раз
        verify(homeWork, atLeastOnce()).exponentiationRecursioFast(2,6);                     //был ли вызван метод хотя бы 1 раз

        verify(homeWork, never()).exponentiationRecursioFast(2,0);                           //метод не был вызван ни разу

        //определяем поведение с использованием doReturn
        doReturn((long)8).when(homeWork).exponentiationRecursioFast(2,3);

        //проверяем действие
        assertEquals(homeWork.exponentiationRecursioFast(2,3),8);
        verify(homeWork).exponentiationRecursioFast(2,3);
    }

    @Test(expected = ArithmeticException.class)
    public void testExponentiationRecursioFastException() throws ArithmeticException{

        ArithmeticException exp = new ArithmeticException();
        int countErrors = 0;

        doThrow(exp).when(homeWork).exponentiationRecursioFast(2,-1);
        doThrow(exp).when(homeWork).exponentiationRecursioFast(2,-2);
        doThrow(exp).when(homeWork).exponentiationRecursioFast(2,-3);

        try{
            assertEquals(homeWork.exponentiationRecursioFast(2,-1), 0);
        } catch (ArithmeticException e){++countErrors;}
        try{
            assertEquals(homeWork.exponentiationRecursioFast(2,-1), 0);
        } catch (ArithmeticException e){++countErrors;}
        try{
            assertEquals(homeWork.exponentiationRecursioFast(2,-2), 0);
        } catch (ArithmeticException e){++countErrors;}
        try {
            assertEquals(homeWork.exponentiationRecursioFast(2,-3), 0);
        } catch (ArithmeticException e){++countErrors;}

        verify(homeWork, times(2)).exponentiationRecursioFast(2,-1);
        verify(homeWork, atLeastOnce()).exponentiationRecursioFast(2,-2);
        verify(homeWork, atLeastOnce()).exponentiationRecursioFast(2,-3);

        if(countErrors==4) throw new ArithmeticException();
    }
}
