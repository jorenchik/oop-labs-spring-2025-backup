package jtm.extra02;

import java.util.ArrayList;
import java.util.List;

public class ArrayListMethods {
    final List<Integer> myList = new ArrayList<>();

    public List<Integer> checkArray(int comparator, int... numbers) {
        myList.clear(); // Ensure we don't keep previous data
        for (int num : numbers) {
            if (num < comparator) {
                myList.add(num);
            }
        }
        return myList;
    }

    public int sumResult() {
        int sum = 0;
        for (int num : myList) {
            sum += num;
        }
        return sum;
    }
}
