package jtm.activity11;

import java.util.LinkedList;

public class ArrayFillerManager {
    static int[] array;                       // array to be filled
    static int latency;                       // emulated latency in ms
    static int seedValue;                     // initial seed value for pseudo-random generator
    private static LinkedList<Thread> threads; // threads for parallel filling

    /**
     * Prepare manager: set parameters and initialize array and thread list
     */
    public static int[] setUp(int arraySize, int latency, int seedValue) {
        ArrayFillerManager.latency = latency;
        ArrayFillerManager.seedValue = seedValue;
        array = new int[arraySize];
        threads = new LinkedList<>();
        return array;
    }

    /**
     * Fill array one element at a time, invoking each filler in current thread
     */
    public static void fillStupidly() {
        for (int i = 0; i < array.length; i++) {
            ArrayFiller filler = new ArrayFiller(latency, seedValue, i, i);
            filler.run();
        }
    }

    /**
     * Fill entire array in one go, using a single filler in current thread
     */
    public static void fillSequentially() {
        ArrayFiller filler = new ArrayFiller(latency, seedValue);
        filler.run();
    }

    /**
     * Fill array in parallel, one filler per element
     */
    public static void fillParalelly() {
        // Clear any previous threads.
        threads.clear();
		
        // Use only as much threads as there are processors.
		final int coresAvailable = Runtime.getRuntime().availableProcessors();
		final int coresWanted = 20;
		final int cores = coresWanted <= coresAvailable ? coresWanted : coresAvailable;
        final int length = array.length;

        // Chunk and execute.
        int chunkSize = (length + cores - 1) / cores;
        System.out.println(chunkSize + " " + cores);
        for (int i = 0; i < cores; i++) {
            int start = i * chunkSize;
            if (start >= length) {
                break;
            }
            int end = Math.min(length - 1, (i + 1) * chunkSize - 1);
            ArrayFiller filler = new ArrayFiller(latency, seedValue, start, end);
            Thread t = new Thread(filler);
            threads.add(t);
            t.start();
        }	

        // Wait for all to complete
        for (Thread t_ : threads) {
            try {
                t_.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
