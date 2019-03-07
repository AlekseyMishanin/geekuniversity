package algorithm.lesson3;

import lombok.NonNull;

/**
 * Класс содержит ряд методов переворачивающих строку
 * */
public class Reverse {

    /**
     * Метод переворачивает строку при помощи стека
     * @param str - исходная строка
     * */
    public static  <T extends CharSequence> String reverse1(@NonNull T str){

        StringBuilder stringBuilder = new StringBuilder();
        MyStackArr<Character> stack = new MyStackArr<>(Character.class);
        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
        }
        while (!stack.isEmpty()){
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.toString();
    }

    /**
     * Метод переворачивает строку при помощи очереди
     * @param str - исходная строка
     * */
    public static  <T extends CharSequence> String reverse2(@NonNull T str){

        StringBuilder stringBuilder = new StringBuilder();
        MyQueueArr<Character> queue = new MyQueueArr<>(Character.class);
        for (int i = str.length()-1; i >= 0; i--) {
            queue.enqueue(str.charAt(i));
        }
        while (!queue.isEmpty()){
            stringBuilder.append(queue.dequeue());
        }
        return stringBuilder.toString();
    }

    /**
     * Метод переворачивает строку при помощи битовой операции XOR (позаимствовано на одном из блогов GB)
     * @param str - исходная строка
     * */
    public static  <T extends CharSequence> String reverse3 (@NonNull T str){

        char[] chars = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            chars[i] = str.charAt(i);
        }
        for (int i = 0; i < chars.length/2; i++) {
            chars[i] ^= chars[chars.length - (i+1)];
            chars[chars.length - (i+1)] ^= chars[i];
            chars[i] ^= chars[chars.length - (i+1)];
        }
        return new String(chars);
    }


}
