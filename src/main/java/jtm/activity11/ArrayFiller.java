package jtm.activity11;

import java.util.Random;
import static jtm.activity11.ArrayFillerManager.array;

public class ArrayFiller implements Runnable {
    int latency;   // required latency time (in milliseconds) to simulate real environment
    int seedValue;    // initial seed value for pseudo-random generator
    int from, to;     // range which should be filled by this filler
    Random random;    // Pseudo-random generator

    public ArrayFiller(int latency, int seedValue) {
        this(latency, seedValue, 0, array.length - 1);
    }

    public ArrayFiller(int latency, int seedValue, int from, int to) {
        this.latency = latency;
        this.seedValue = seedValue;
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(latency);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        random = new Random(seedValue + from);
        for (int i = from; i <= to; i++) {
            array[i] = new Random(seedValue + i).nextInt();
        }
    }
}
