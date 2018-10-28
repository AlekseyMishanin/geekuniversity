package javacoreadvanced.lesson3.basic.phonebook;

import java.util.*;

/**
 * @author Mishanin Aleksey
 * */
public class PhoneBook <T extends Comparable<T>, E extends Comparable<E>> {

    //ссылочная интерфейсная переменная под объект HashMap
    final private Map<T, Set<E>> phonebook= new HashMap<>();

    /**
     * Метод добавления новой записи в телефонный справочник
     * @param name - фамилия (ключ)
     * @param phone - номер телефона (значение)
     * */
    public void add(T name,E phone){
        final Set<E> lPhone = phonebook.getOrDefault(name, new HashSet<>());
        lPhone.add(phone);
        phonebook.put(name,lPhone);
    }

    /**
     * Метод поиска значения по ключу
     * @param name - фамилия (ключ)
     * */
    public Set<E> get (T name){
        return phonebook.get(name);
    }
}
