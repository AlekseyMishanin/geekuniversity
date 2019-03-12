package algorithm.lesson4;

public class Main {

    public static void main(String[] args) {

        MyLinkedList<Character> list = new MyLinkedList<>();

        list.addFirst('1');
        list.addFirst('2');

        list.addFirst('3');
        list.addFirst('4');
        list.addFirst('5');
        list.addFirst('6');
        list.addFirst('7');

        System.out.println(list.get(6));
        System.out.println(list.get(5));
        System.out.println(list.get(4));

    }
}
