package jtm.extra01;

public class GetOne {

    public int iterations(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Input must be a positive integer.");
        }

        int iterationCount = 0;
        while (number != 1) {
            if (number % 2 == 0) {
                number /= 2;
            } else {
                number = number * 3 + 1;
            }
            iterationCount++;
        }
        return iterationCount;
    }

    public int theMostComplexNo(int maxNumber) {
        if (maxNumber < 1) {
            throw new IllegalArgumentException("maxNumber must be >= 1.");
        }

        int maxIterations = -1;
        int numberWithMaxIterations = 1;

        for (int i = 1; i <= maxNumber; i++) {
            int currentIterations = iterations(i);
            if (currentIterations > maxIterations) {
                maxIterations = currentIterations;
                numberWithMaxIterations = i;
            }
        }

        return numberWithMaxIterations;
    }
}
