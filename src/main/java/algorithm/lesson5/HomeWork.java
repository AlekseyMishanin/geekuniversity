package algorithm.lesson5;

/*
1. Написать программу по возведению числа в степень с помощью рекурсии и с помощью циклического оператора.
a^b = (a * ... * a) b раз
f(a,b) = f(a,b-1) * a
2. Познакомиться с головоломкой Ханойские башни. Реализовать рекурсивный и циклический алгоритмы решения этой головоломки.
*/


import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HomeWork {

    public static void main(String[] args) {

        HomeWork hw = new HomeWork();
        System.out.println(hw.exponentiationCycle(2,4));
        System.out.println(hw.exponentiationRecursioSlow(2,7));
        System.out.println(hw.exponentiationRecursioFast(2,7));

        LinkedList<Integer> stemA, stemB, stemC;
        stemA = new LinkedList<>();                     //создаем первый стержень
        stemB = new LinkedList<>();                     //создаем второй стержень
        stemC = new LinkedList<>();                     //создаем третий стержень

        hw.towerOfHanoiCycle(3, stemA, stemB, stemC);

        stemA = new LinkedList<>();                     //создаем первый стержень
        stemB = new LinkedList<>();                     //создаем второй стержень
        stemC = new LinkedList<>();                     //создаем третий стержень

        hw.towerOfHanoiRecursio(3, stemA, stemB, stemC);
    }

    /**
     * Метод возведения числа в степень через цикл.
     * @param value - число
     * @param extent - степень
     * */
    public long exponentiationCycle(int value, int extent){

        if(extent < 0) throw new ArithmeticException("The degree cannot be a negative number.");
        long result = value;
        for (int i = 2; i < extent + 1; i++){
            result *= value;
        }
        return result;
    }

    /**
     * Метод возведения числа в степень через рекурсию (медленный вариант).
     * @param value - число
     * @param extent - степень
     * */
    public long exponentiationRecursioSlow(int value, int extent){

        if(extent < 0) throw new ArithmeticException("The degree cannot be a negative number.");
        if(extent == 1) return value;
        return exponentiationRecursioSlow(value,extent - 1) * value;
    }

    /**
     * Метод возведения числа в степень через рекурсию (быстрый вариант).
     * @param value - число
     * @param extent - степень
     * */
    public long exponentiationRecursioFast(int value, int extent){

        if(extent < 0) throw new ArithmeticException("The degree cannot be a negative number.");
        if(extent == 1) return value;
        long res = exponentiationRecursioFast(value, extent / 2);
        return extent % 2 == 1 ? res * res * value : res * res;
    }

    /**
     * Метод циклического решения головоломки Ханойская башня.
     * @param count - количество дисков, размещаемых на первом стержне
     * */
    public void towerOfHanoiCycle(int count, Deque<Integer> stemA, Deque<Integer> stemB, Deque<Integer> stemC){

        if(count < 0) throw new ArithmeticException("The count cannot be a negative number.");

        //помещаем на первый стержень диски в порядке уменьшения диаметра диска (от большего к меньшему)
        for (int i = count; i > 0; i--) {
            stemA.push(i);
        }

        System.out.println("Before:");
        System.out.println("Первый стержень - " + stemA);
        System.out.println("Второй стержень - " + stemB);
        System.out.println("Третий стержень - " + stemC);

        //начинаем перекладывать диски с одного стержная на другой пока все диски не окажутся на третьем стержне в порядке уменьшения диаметра диска
        while (stemC.size() != count){

            //первый обмен дисками - между 1-м и 2-м стержнем если количество дисков четное число иначе между 1-м и 3-м
            try {
                if(count%2==0){                             //четное кол-во дисков
                    if (stemA.peek() < stemB.peek()) {      //диаметр диска на вершине 2-го стержня больше диаметра диска на вершине 1-го стрержня
                        stemB.push(stemA.pop());            //перекладываем диск с вершины 1-го стержня на вершину 2-го стержня
                    } else {                                //иначе
                        stemA.push(stemB.pop());            //перекладываем диск с вершины 2-го стержня на вершину 1-го стержня
                    }
                }
                if(count%2==1){                             //нечетное кол-во дисков
                    if (stemA.peek() < stemC.peek()) {      //диаметр диска на вершине 3-го стержня больше диаметра диска на вершине 1-го стрержня
                        stemC.push(stemA.pop());            //перекладываем диск с вершины 1-го стержня на вершину 3-го стержня
                    } else {                                //иначе
                        stemA.push(stemC.pop());            //перекладываем диск с вершины 3-го стержня на вершину 1-го стержня
                    }
                }
            } catch (NullPointerException exp){             //если какой-либо из стержней пустой (без дисков)
                try{
                    if(count%2==0 && stemB.isEmpty()) {             //если четное кол-во дисков и пуст 2-й стержень
                        stemB.push(stemA.pop());                    //перекладываем диск с вершины 1-го стержня на вершину 2-го стержня
                    } else if(count%2==1 &&  stemC.isEmpty()) {     //если нечетное кол-во дисков и пуст 3-й стержень
                        stemC.push(stemA.pop());                    //перекладываем диск с вершины 3-го стержня на вершину 1-го стержня
                    } else if(count%2==0 && stemA.isEmpty()) {      //если четное кол-во дисков и пуст 1-й стержень
                        stemA.push(stemB.pop());                    //перекладываем диск с вершины 2-го стержня на вершину 1-го стержня
                    } else if(count%2==1 && stemA.isEmpty()) {      //если нечетное кол-во дисков и пуст 1-й стержень
                        stemA.push(stemC.pop());                    //перекладываем диск с вершины 3-го стержня на вершину 1-го стержня
                    }
                } catch (NoSuchElementException e){
                    break;                                          //3-й стержень наполнен дисками и мы пытаемся извлечь диск из пустого 1-го или 2-го стержня
                }
            }

            //второй обмен дисками - между 1-м и 3-м стержнем если количество дисков четное число иначе между 1-м и 2-м
            try {
                if(count%2==0){                             //четное кол-во дисков
                    if (stemA.peek() < stemC.peek()) {      //диаметр диска на вершине 3-го стержня больше диаметра диска на вершине 1-го стрержня
                        stemC.push(stemA.pop());            //перекладываем диск с вершины 1-го стержня на вершину 3-го стержня
                    } else {                                //иначе
                        stemA.push(stemC.pop());            //перекладываем диск с вершины 3-го стержня на вершину 1-го стержня
                    }
                }
                if(count%2==1){                             //нечетное кол-во дисков
                    if (stemA.peek() < stemB.peek()) {      //диаметр диска на вершине 2-го стержня больше диаметра диска на вершине 1-го стрержня
                        stemB.push(stemA.pop());            //перекладываем диск с вершины 1-го стержня на вершину 2-го стержня
                    } else {                                //иначе
                        stemA.push(stemB.pop());            //перекладываем диск с вершины 2-го стержня на вершину 1-го стержня
                    }
                }
            } catch (NullPointerException exp){             //если какой-либо из стержней пустой (без дисков)
                try {
                    if (count % 2 == 1 && stemB.isEmpty()) {        //если нечетное кол-во дисков и пуст 2-й стержень
                        stemB.push(stemA.pop());                    //перекладываем диск с вершины 1-го стержня на вершину 2-го стержня
                    } else if (count % 2 == 0 && stemC.isEmpty()) { //если четное кол-во дисков и пуст 3-й стержень
                        stemC.push(stemA.pop());                    //перекладываем диск с вершины 3-го стержня на вершину 1-го стержня
                    } else if (count % 2 == 1 && stemA.isEmpty()) { //если нечетное кол-во дисков и пуст 1-й стержень
                        stemA.push(stemB.pop());                    //перекладываем диск с вершины 2-го стержня на вершину 1-го стержня
                    } else if (count % 2 == 0 && stemA.isEmpty()) { //если четное кол-во дисков и пуст 1-й стержень
                        stemA.push(stemC.pop());                    //перекладываем диск с вершины 3-го стержня на вершину 1-го стержня
                    }
                } catch (NoSuchElementException e){                 //3-й стержень наполнен дисками и мы пытаемся извлечь диск из пустого 1-го или 2-го стержня
                    break;
                }
            }

            //третий обмен дисками - между 2-м и 3-м стержнем
            try{
                if(stemB.peek() < stemC.peek()){            //диаметр диска на вершине 3-го стержня больше диаметра диска на вершине 2-го стрержня
                    stemC.push(stemB.pop());                //перекладываем диск с вершины 2-го стержня на вершину 3-го стержня
                } else {                                    //иначе
                    stemB.push(stemC.pop());                //перекладываем диск с вершины 3-го стержня на вершину 2-го стержня
                }
            } catch (NullPointerException exp){             //если какой-либо из стержней пустой (без дисков)
                if(stemC.isEmpty()) {                       //если пуст 3-й стержень
                    stemC.push(stemB.pop());                //перекладываем диск с вершины 2-го стержня на вершину 3-го стержня
                } else if(stemB.isEmpty()) {                //если пуст 2-й стержень
                    stemB.push(stemC.pop());                //перекладываем диск с вершины 3-го стержня на вершину 2-го стержня
                }
            }
        }
        System.out.println("After:");
        System.out.println("Первый стержень - " + stemA);
        System.out.println("Второй стержень - " + stemB);
        System.out.println("Третий стержень - " + stemC);
    }

    /**
     * Метод рекурсивного решения головоломки Ханойская башня.
     * @param count - количество дисков, размещаемых на первом стержне
     * */
    public void towerOfHanoiRecursio(int count, Deque<Integer> stemA, Deque<Integer> stemB, Deque<Integer> stemC){

        if(count < 0) throw new ArithmeticException("The count cannot be a negative number.");

        //помещаем на первый стержень диски в порядке уменьшения диаметра диска (от большего к меньшему)
        for (int i = count; i > 0; i--) {
            stemA.push(i);
        }
        System.out.println("Before:");
        System.out.println("Первый стержень - " + stemA);
        System.out.println("Второй стержень - " + stemB);
        System.out.println("Третий стержень - " + stemC);

        recursivePermutation(stemA, stemB, stemC, count);       //рекурсивно перемещаем диски

        System.out.println("After:");
        System.out.println("Первый стержень - " + stemA);
        System.out.println("Второй стержень - " + stemB);
        System.out.println("Третий стержень - " + stemC);
    }

    /**
     * Служебный метод, реализующий рекурсивное перемещение дисков с 1-го стержня на 3-й через промежуточный 2-й.
     * */
    private void recursivePermutation(Deque<Integer> from, Deque<Integer> to, Deque<Integer> inter, int count){

        if(count<=1){
            try{
                if (inter.peek() > from.peek()) {
                    inter.push(from.pop());
                } else {
                    from.push(inter.pop());
                }
            } catch (NullPointerException exp){
                try {
                    if (inter.isEmpty()) {
                        inter.push(from.pop());
                    } else if (from.isEmpty()) {
                        from.push(inter.pop());
                    }
                } catch (NoSuchElementException e){
                    //на тот случай если count <= 0
                }
            }
        }
        else{
            recursivePermutation(from, inter, to, count-1);     //рекурсивно строим башню n-1 на 2-м стержне
            try{
                if (inter.peek() > from.peek()) {
                    inter.push(from.pop());
                } else {
                    from.push(inter.pop());
                }
            } catch (NullPointerException exp){
                if(inter.isEmpty()) {
                    inter.push(from.pop());
                } else if(from.isEmpty()) {
                    from.push(inter.pop());
                }
            }
            recursivePermutation(to, from, inter, count-1);     //рекурсивно строим башню n-1 на 3-м стержне
        }
    }
}
