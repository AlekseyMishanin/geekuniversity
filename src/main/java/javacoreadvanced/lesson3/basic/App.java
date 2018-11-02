package javacoreadvanced.lesson3.basic;

/*
1. Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся).
Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
Посчитать, сколько раз встречается каждое слово.
2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
В этот телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать
номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов
(в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
Желательно как можно меньше добавлять своего, чего нет в задании (т.е. не надо в телефонную запись добавлять
еще дополнительные поля (имя, отчество, адрес), делать взаимодействие с пользователем через консоль и т.д.).
Консоль желательно не использовать (в том числе Scanner), тестировать просто из метода main(),
прописывая add() и get().
*/

import javacoreadvanced.lesson3.basic.phonebook.PhoneBook;
import javacoreadvanced.lesson3.basic.stringparse.StringParse;

public class App {
    public static void main(String[] args) {
        final StringParse<String> strArr = new StringParse<> ();
        strArr.addAll("zero", "one", "two", "five", "four", "five", "zero", "seven", "eight", "zero",
                "ten", "eleven", "twelve", "thirteen", "five", "fifteen", "zero", "seventeen", "eighteen",
                "five", "ten");
        strArr.printUniqueWords();
        strArr.printCountWords();

        PhoneBook<String,String> phoneBook = new PhoneBook<>();
        phoneBook.add("Иван", "88001001010");
        phoneBook.add("Иван", "88001001010");
        phoneBook.add("Паша", "88001001011");
        phoneBook.add("Саша", "88001001012");
        phoneBook.add("Вася", "88001001013");
        phoneBook.add("Толя", "88001001014");
        phoneBook.add("Коля", "88001001015");
        phoneBook.add("Толя", "88001001016");
        System.out.println(phoneBook.get("Иван"));
        System.out.println(phoneBook.get("Толя"));
        System.out.println(phoneBook.get("Коля"));
    }


}
