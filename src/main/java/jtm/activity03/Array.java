package jtm.activity03;

import java.util.Arrays;

public class Array {
    static int[] array;

    public static void main(String[] args) {
    	array = new int[args.length];
    	for (int i = 0; i < args.length; i++) {
    		array[i] = Integer.parseInt(args[i]);
    	}
    	Arrays.sort(array);
    }

    public static void printSortedArray() {
    	System.out.println(Arrays.toString(array));
    }

    public static int[] returnSortedArray() {
        return array;
    }

}
