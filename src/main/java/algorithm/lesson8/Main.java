package algorithm.lesson8;

public class Main {

    public static void main(String[] args) {

        MyChainingHashMap<Character,Integer> map = new MyChainingHashMap(5);
        map.put('a',1);
        map.put('h',1);
        map.put('j',1);
        map.put('u',1);
        map.remove('a');
        map.remove('h');
        map.remove('j');
    }

}
