package javacoreadvanced.lesson5.array;

import lombok.NonNull;
import java.util.Arrays;

/**
 * Класс инкапсулирует работу с n-ым количеством потоков
 * @author - Mishanin Aleksey
 * */
public class MultithreadedArray {

    public static void processArray(final int countThread, @NonNull final float ... values) throws InterruptedException {

        //исходный массив
        final float [] array = values;
        //количество потоков
        final int cThreads = countThread;
        //массив потоков
        final Thread[] threads = new Thread[cThreads];
        //двухмерный массив для работы в отдельных потоках
        final float [][] lArr = new float[cThreads][];
        //длина исходного массива
        final int size = array.length;
        //размер массивов используемых в потоках (за исключением последнего массива при определенных условиях)
        final int localsize = (int)Math.ceil((1.0*size)/(1.0*cThreads));
        Arrays.fill(array,1);
        final long startCopyFirst = System.currentTimeMillis();
        for (int i = 0; i < cThreads; i++) {
            final int j = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    lArr[j] = Arrays.copyOfRange(array,localsize*j, (localsize + localsize*j) < size ? (localsize + localsize*j) : size);
                }
            });
            threads[i].start();
        }
        for (final Thread th: threads) {th.join();}
        final long startProcess = System.currentTimeMillis();
        for (int i = 0; i < cThreads; i++) {
            final int j = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int k = 0; k < lArr[j].length; k++) {
                        lArr[j][k] = (float)(lArr[j][k] * Math.sin(0.2f + (j*localsize+k) / 5) * Math.cos(0.2f + (j*localsize+k) / 5) * Math.cos(0.4f +(j*localsize+k) / 2));
                    }
                }
            });
            threads[i].start();
        }
        for (final Thread th: threads) {th.join();}
        final long startCopyLast = System.currentTimeMillis();
        for (int i = 0; i < cThreads; i++) {
            final int j = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.arraycopy(lArr[j],0,array,localsize*j, lArr[j].length);
                }
            });
            threads[i].start();
        }
        for (final Thread th: threads) {th.join();}
        final long endCopyLast = System.currentTimeMillis();
        System.out.println("Time to first copy = " + (startProcess - startCopyFirst) + ";\n"+
                "Time to process = " + (startCopyLast - startProcess) + ";\n"+
                "Time to last copy = " + (endCopyLast - startCopyLast) + ";");
    }
}
