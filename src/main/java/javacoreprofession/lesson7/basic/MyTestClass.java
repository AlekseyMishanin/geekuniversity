package javacoreprofession.lesson7.basic;

/**
 * Тестовый класс. Содержит набор тестовых методов.
 * @author - Mishanin Aleksey
 * */
public class MyTestClass {

    @BeforeSuite
    public void Test1(){
        System.out.println("BeforeSuite");
    }

    @Test(priority = 2)
    public void Test2(){
        System.out.println("Test2");
    }

    @Test(priority = 4)
    public void Test3() {
        System.out.println("Test3");
    }
    @Test(priority = 1)
    public void Test4(){
        System.out.println("Test4");
    }
    @Test
    public void Test5(){
        System.out.println("Test5");
    }
    @AfterSuite
    public void Test6(){
        System.out.println("AfterSuite");
    }
}
