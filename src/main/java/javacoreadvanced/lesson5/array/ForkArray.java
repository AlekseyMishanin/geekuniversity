package javacoreadvanced.lesson5.array;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * Класс инкапсулирует работу с несколькими процессорами
 * @author - Mishanin Aleksey
 * */
public class ForkArray extends RecursiveAction {

    private final int seqArray = 1000;

    double[] data;

    int start, end;

    public ForkArray(double[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    protected void compute(){
        if((end-start)<seqArray){
            for (int i = start; i < end; i++) {
                data[i] = (double) (data[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        } else {
            final int middle = (start+end)/2;
            invokeAll(new ForkArray(data,start,middle),new ForkArray(data, middle, end));
        }
    }
}
