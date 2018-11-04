package javacoreadvanced.lesson5.array;

import lombok.NonNull;
import java.util.Arrays;

/**
 * Класс инкапсулирует работу без потоков
 * @author - Mishanin Aleksey
 * */
public class SimpleArray {

    public static void processArray(@NonNull final float ... values){

        final float [] array = values;
        Arrays.fill(array,1);
        final long a = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Time without threads = " + (System.currentTimeMillis() - a));
    }
}
