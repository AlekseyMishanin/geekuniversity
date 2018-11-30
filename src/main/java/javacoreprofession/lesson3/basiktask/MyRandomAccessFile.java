package javacoreprofession.lesson3.basiktask;

import java.io.*;

/**
 * Класс инкапсулирует файл произвольного доступа.
 * @author - Mishanin Aleksey
 * */
public class MyRandomAccessFile {

    private RandomAccessFile file;      //файл
    private final int size = 1800;      //размер страницы
    private int countPage = 0;          //кол-во страниц
    private byte[] buffer;              //буффер для вывода данных

    public MyRandomAccessFile(String name) throws FileNotFoundException {
        file = new RandomAccessFile(new File("/",name),"r");
        buffer = new byte[size];
    }

    /**
     * Метод принимает от пользователя номер страницы файла и выводит содержимое заданной страницы на экран. Если
     * введена несуществующая страницы формируется исключительная ситуация MyPageException
     * */
    public void work() {
        getCountPage();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Файл содержит " + countPage + " страниц");
        while (true) {
            System.out.println("Выберите страницу для чтения(exit - выход): ");
            try {
                String str = in.readLine();
                if(str.equals("exit")) return;
                int numberPage = Integer.parseInt(str);
                if(numberPage>countPage||numberPage<0) {
                    throw new MyPageException();
                } else{
                    file.seek((numberPage-1)*size);
                    file.read(buffer);
                    System.out.print(new String(buffer,"US-ASCII"));
                    System.out.println();
                }
            }catch (MyPageException|NumberFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод определяет кол-во страниц в файле. Округление в большую сторону.
     * */
    private void getCountPage(){
        try {
            countPage = (int)Math.ceil(1.0*file.length()/size);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        if(file!=null) {
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
