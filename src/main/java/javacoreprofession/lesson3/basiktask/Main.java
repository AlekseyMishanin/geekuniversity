package javacoreprofession.lesson3.basiktask;

/*
1.Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
2.Последовательно сшить 5 файлов в один (файлы примерно 100 байт). Может пригодиться
следующая конструкция: ArrayList<InputStream> al = new ArrayList<>(); ...
Enumeration<InputStream> e = Collections.enumeration(al);
3.Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль.
Контролируем время выполнения: программа не должна загружаться дольше 10 секунд, а чтение – занимать свыше 5 секунд.
*/


import java.io.*;
import java.util.*;

public class Main {

    private static int SIZE = 50;

    public static void main(String[] args) throws IOException {

        //решение первой задачи
        MyByteFile myfile = new MyByteFile().createFile("file").randomWrite();
        byte[] byteArr = new byte[SIZE];
        myfile.read(byteArr);
        System.out.print(new String(byteArr, "US-ASCII"));

        //решение второй задачи
        Vector<String> files = new Vector<>();
        for (int i = 0; i < 5; i++) {
            files.addElement(new MyByteFile(100).createFile("file"+i).randomWrite().getNameFile());
        }
        MyInputStreamEnumerator myise = new MyInputStreamEnumerator(files);
        InputStream input = new SequenceInputStream(myise);
        int c;
        while((c=input.read())!=-1){
            System.out.print((char)c);
        }
        System.out.println();

        //решение третьей задачи
        MyByteFile myfile1 = new MyByteFile(12000).createFile("file5").randomWrite();
        MyRandomAccessFile myRandomAccessFile = new MyRandomAccessFile(myfile1.getNameFile());
        myRandomAccessFile.work();
        myRandomAccessFile.close();
    }
}
