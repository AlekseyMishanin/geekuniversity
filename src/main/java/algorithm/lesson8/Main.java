package algorithm.lesson8;

public class Main {

    public static void main(String[] args) {

        char[] charsAdd = new char[]{'a','h','j','u','y','o','r','v','z','u'};
        char[] charsDel = new char[]{'a','h','j','j','z','o','r'};

        MylinearProbbingHashMap<Character,Integer> map1 = new MylinearProbbingHashMap(5);
        for (char ch:
        charsAdd) {
            map1.put(ch,1);
        }
        System.out.println(map1);
        for (char ch:
                charsDel) {
            map1.remove(ch);
        }
        System.out.println(map1);

        MyChainingHashMap<Character,Integer> map2 = new MyChainingHashMap(5);
        for (char ch:
                charsAdd) {
            map2.put(ch,1);
        }
        System.out.println(map2);
        for (char ch:
                charsDel) {
            map2.remove(ch);
        }
        System.out.println(map2);

        MyDoubleHashingHashMap<Character,Integer> map3 = new MyDoubleHashingHashMap(5);
        for (char ch:
                charsAdd) {
            map3.put(ch,1);
        }
        System.out.println(map3);
        for (char ch:
                charsDel) {
            map3.remove(ch);
        }
        System.out.println(map3);
    }


}