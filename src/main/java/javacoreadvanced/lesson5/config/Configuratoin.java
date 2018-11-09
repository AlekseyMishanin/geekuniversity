package javacoreadvanced.lesson5.config;

import lombok.Getter;
import lombok.Setter;

/**
 * Класс содержит параметры, используемые при работе с массивами
 * @author - Mishanin Aleksey
 * */

@Getter
@Setter
public class Configuratoin {

    //размер массива
    private int size;
    //количество потоков
    private int countThreads;

    public Configuratoin(){
        size = 10000000;
        countThreads = 4;
    }
}
