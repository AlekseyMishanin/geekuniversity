package javacoreprofession.lesson8.additional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Main {

    //количество деревьев в лесу
    private static int COUNTTRUNK = 100;
    //количество видов деревьев
    private static int COUNTTYPE = 5;
    //перечень конкретных видов деревьев
    private enum TREE {

        BIRCH,  //береза
        PINE,   //сосна
        SPRUCE, //ель
        FIR,    //пихта
        LIME;   //липа

        //по порядковому номеру вида выводим наименование вида дерева
        public static TREE get(int index){
            for (TREE tree: TREE.values()) {
                if(tree.ordinal()==index) return tree;
            }
            return null;
        }
    }

    //набор сторон прямоугольника
    private enum FLAG {TOP, LEFT, RIGHT, BUTTON};
    //количество строк в матрице
    public static  int ROW = 7;
    //количество столбцов в матрице
    public static int COLUMN = 6;

    public static void main(String[] args) {

        //массив леса
        TREE[] forest = new TREE[COUNTTRUNK];
        //блокнот лесника: ключ - наименование вида дерева, значение - количество наименований
        HashMap<TREE,Integer> notebook = new HashMap<>();
        //переменная рандома
        Random random = new Random();
        //помечаем в блокноте лесника наименования видов деревьев, которые могут встериться в лесу
        for (TREE tree: TREE.values()) {
            notebook.put(tree,0);
        }
        //рандомно высаживаем в лесу деревья
        for (int i = 0; i < COUNTTRUNK; i++) {
            forest[i] = TREE.get(random.nextInt(COUNTTYPE));
        }
        //Выводим содержимое леса
        System.out.println(Arrays.toString(forest));
        //Выводим содержимое блокнота лесника перед обходом леса
        System.out.println(notebook);
        //отправляем лесника в лес для сбора данных
        for (TREE tree : forest) {
            notebook.computeIfPresent(tree,(a,b)->{return ++b;});
        }
        //выводим содержимое блокнтона лесника после обхода леса
        System.out.println(notebook);

//--------------------------------------------------------------------------------------------------------------------//

        //матрица размерности ROW*COLUMN
        int[][] arr = new int[ROW][COLUMN];
        //начальная и конечная строка используемая при обходе матрицы
        int row_start=0, row_end = ROW-1;
        //начальный и конечный столбец используемый при обходе матрицы
        int column_start=0, column_end = COLUMN-1;
        //флаг стороны прямоугольника для обхода
        FLAG fl = FLAG.TOP;
        //кол-во элементов в матрице
        int allElement = ROW*COLUMN;
        for (int i = 0; i < allElement;) {
            switch (fl){
                case TOP:
                    for (int j = column_start; j <= column_end && i < allElement; j++,i++) {
                        arr[row_start][j] = i;
                    }
                    ++row_start;
                    fl=FLAG.RIGHT;
                    break;
                case RIGHT:
                    for (int j = row_start; j <= row_end && i < allElement; j++,i++) {
                        arr[j][column_end] = i;
                    }
                    --column_end;
                    fl=FLAG.BUTTON;
                    break;
                case BUTTON:
                    for (int j = column_end; j >= column_start && i < allElement; j--,i++) {
                        arr[row_end][j] = i;
                    }
                    --row_end;
                    fl=FLAG.LEFT;
                    break;
                case LEFT:
                    for (int j = row_end; j >= row_start && i < allElement; j--,i++) {
                        arr[j][column_start] = i;
                    }
                    ++column_start;
                    fl=FLAG.TOP;
                    break;
            }
        }
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
    }
}
