package javacoreprofession.lesson7.basic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация помечает тестовый метод. Аннотация содержит параметр priority определяющий приоритет выполнения
 * метода. Приоритет может быть в диапазоне от 1 до 10.
 * @author - Mishanin Aleksey
 * */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
    int priority() default 5;
}
