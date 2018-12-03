package javacoreprofession.lesson3.basiktask;

import lombok.NonNull;

import java.io.*;
import java.util.Random;

/**
 * Класс инкапсулирует файл. Предоставляет возможность наполнить файл случайными символами
 * @author - Mishanin Aleksey
 * */
public class MyByteFile {

    private int size;                       //размер файла
    private File file;                      //файл
    private Random rand = new Random();     //рандом

    public MyByteFile(){this.size=50;}

    public MyByteFile(int size) {
        this.size=size;
    }

    public MyByteFile createFile (@NonNull final String name){
        file = new File("/", name);
        return this;
    }

    /**
     * Метод наполняет файл случайными буквами английского алфавита
     * */
    public MyByteFile randomWrite() throws IOException {
        try(FileOutputStream out = new FileOutputStream(file)){
            for (int i = 0; i < size; i++) {
                out.write(rand.nextInt(25)+'A');
            }
        }
        return this;
    }

    /**
     * Метод читает данные из файла в массив
     * @param arr - массив для записи данных из файла
     * */
    public void read(@NonNull final byte[] arr) throws IOException {
        try(FileInputStream in = new FileInputStream(file)){
            in.read(arr);
        }
    }

    public String getNameFile(){
        return file.getName();
    }
}
