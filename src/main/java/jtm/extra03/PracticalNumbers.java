package jtm.extra03;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PracticalNumbers {

    public String getPracticalNumbers(int from, int to) {
        if (from > to || from < 1) {
            throw new IllegalArgumentException("Invalid range.");
        }

        List<Integer> practicals = new ArrayList<>();

        for (int n = from; n <= to; n++) {
            if (isPractical(n)) {
                practicals.add(n);
            }
        }

        return practicals.toString();
    }

    private boolean isPractical(int n) {
        if (n == 1) return true;

        Map<Integer, Integer> primeFactors = primeFactorization(n);
        List<Integer> primes = new ArrayList<>(primeFactors.keySet());

        if (primes.get(0) != 2) return false;

        long sigma = 1;
        long product = 1;

        for (int i = 0; i < primes.size(); i++) {
            int p = primes.get(i);
            int exp = primeFactors.get(p);

            if (i > 0 && p > sigma + 1) {
                return false;
            }

            product *= Math.pow(p, exp);
            sigma = sumOfDivisors(product);
        }

        return true;
    }

    private Map<Integer, Integer> primeFactorization(int n) {
        Map<Integer, Integer> factors = new TreeMap<>();
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                factors.put(i, factors.getOrDefault(i, 0) + 1);
                n /= i;
            }
        }
        if (n > 1) {
            factors.put(n, 1);
        }
        return factors;
    }

    private long sumOfDivisors(long n) {
        long sum = 1;
        long temp = n;

        for (int p = 2; p * p <= temp; p++) {
            long currentSum = 1;
            long term = 1;

            while (n % p == 0) {
                n /= p;
                term *= p;
                currentSum += term;
            }
            sum *= currentSum;
        }

        if (n > 1) sum *= (1 + n);

        return sum;
    }
}
