package javacoreprofession.lesson7.basic;

import java.lang.reflect.Method;

/**
 * Класс анализирует другой класс на наличие аннотаций описывающих тестовые методы. При наличии в методе необходимых
 * аннотаций выполняет метод класса.
 * @author - Mishanin Aleksey
 * */
public class MyAnnotationAnalyzer {

    /**
     * Метод запускает тестовые методы класса переданного в качестве аргумента
     * @param clazz - класс с набором тестовых методов
     * */
    public static void start(Class<?> clazz) throws MyRuntimeException{
        //переменная содержит ссылку на объект класса Class<?>
        Object object = null;
        try {
            object = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Method[] methods = clazz.getMethods();
        //флаг подтверждающий запуск тестового метода с аннотацией BeforeSuite
        boolean flagBeforeSuite = false;
        //флаг подтверждающий запуск тестового метода с аннотацией AfterSuite
        boolean flagAfterSuite = false;

        //запускаем тест с аннтоацией BeforeSuite
        for (Method method : methods) {
            try {
                if(method.isAnnotationPresent(BeforeSuite.class)&&!flagBeforeSuite){
                    method.invoke(object);
                    flagBeforeSuite = true;
                } else if(method.isAnnotationPresent(BeforeSuite.class)&&flagBeforeSuite){
                    throw new MyRuntimeException("More than one BeforeSuite method found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //запускаем тест/тесты с аннтоацией Test
        for (int i = 0; i < 10; i++) {
            for (Method method : methods) {
                try {
                    if(method.isAnnotationPresent(Test.class)&&method.getAnnotation(Test.class).priority()==i){
                        method.invoke(object);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        //запускаем тест с аннтоацией AfterSuite
        for (Method method : methods) {
            try {
                if(method.isAnnotationPresent(AfterSuite.class)&&!flagAfterSuite){
                    method.invoke(object);
                    flagAfterSuite = true;
                } else if(method.isAnnotationPresent(AfterSuite.class)&&flagAfterSuite){
                    throw new MyRuntimeException("More than one AfterSuite method found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
