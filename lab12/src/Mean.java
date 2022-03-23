import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Mean {
    static double[] array;
    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);
    static Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        initArray(128000000);
        for(int cnt:new int[]{1,2,4,8,16,32,64,128}){
            parallelMeanVol1(cnt);
        }
    }

    static class MeanCalc extends Thread{
        private final int start;
        private final int end;
        double mean = 0;

        MeanCalc(int start, int end){
            this.start = start;
            this.end = end;
        }

        public void run(){
            for (int i = start; i < end; i++) {
                mean = mean + array[i];
            }
            mean /= end - start;

            try {
                if (results != null) results.put(mean);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf(Locale.US,"%d-%d mean=%f\n",start,end,mean);

            semaphore.release();
        }
    }

    static void initArray(int size){
        array = new double[size];
        for(int i = 0; i < size; i++){
            array[i] = Math.random() * size / (i + 1);
        }
    }

    /**
     * Oblicza średnią wartości elementów tablicy array uruchamiając równolegle działające wątki.
     * Wypisuje czasy operacji
     * @param cnt - liczba wątków
     */
    static void parallelMeanVol1(int cnt) throws InterruptedException {
        // utwórz tablicę wątków
        MeanCalc threads[] = new MeanCalc[cnt];
        // utwórz wątki, podziel tablice na równe bloki i przekaż indeksy do wątków
        // załóż, że array.length dzieli się przez cnt)
        int threadArrayLength = array.length / cnt;
        for(int i = 0; i < cnt; i++){
            threads[i] = new MeanCalc(i * threadArrayLength, (i + 1) * threadArrayLength);
        }
        double t1 = System.nanoTime() / 1e6;
        Arrays.stream(threads).forEach(Thread::start);
        //uruchom wątki
        double t2 = System.nanoTime() / 1e6;
        // czekaj na ich zakończenie używając metody ''join''
        for(MeanCalc mc : threads) {
            mc.join();
        }
        // oblicz średnią ze średnich
        double t3 = System.nanoTime() / 1e6;
        System.out.printf(Locale.US,"size = %d cnt = %d > t2 - t1 = %f t3 - t1 = %f mean = %f\n\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                Arrays.stream(threads).mapToDouble(thread -> thread.mean).sum() / cnt);
    }

    /**
     * Oblicza średnią wartości elementów tablicy array uruchamiając równolegle działające wątki.
     * Wypisuje czasy operacji
     * @param cnt - liczba wątków
     */
    static void parallelMeanVol2(int cnt) throws InterruptedException {
        float sum = 0;
        results = new ArrayBlockingQueue<>(cnt);
        MeanCalc threads[] = new MeanCalc[cnt];
        int threadArrayLength = array.length / cnt;

        for(int i = 0; i < cnt; i++){
            threads[i] = new MeanCalc(i * threadArrayLength, (i + 1) * threadArrayLength);
        }

        double t1 = System.nanoTime() / 1e6;
        Arrays.stream(threads).forEach(Thread::start);

        double t2 = System.nanoTime() / 1e6;
        for(int i = 0; i < cnt; i++) {
            sum += results.take();
        }
        try {
            semaphore.acquire(cnt);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        // oblicz średnią ze średnich
        double t3 = System.nanoTime() / 1e6;
        System.out.printf(Locale.US,"size = %d cnt = %d > t2 - t1 = %f t3 - t1 = %f mean = %f\n\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                Arrays.stream(threads).mapToDouble(thread -> thread.mean).sum() / cnt);
    }
}