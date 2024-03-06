package com.icboluo.leetcode.高效寻找素数;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2024-03-06 22:50
 */
class N1175_质数排列 {
    public static void main(String[] args) {
        System.out.println(new N1175_质数排列().numPrimeArrangements(5));
        System.out.println(new N1175_质数排列().numPrimeArrangements(100));
    }
    // 质数排列 math问题
    public int numPrimeArrangements(int n) {
        int primeCount = countPrimes(n);
        BigInteger factorial1 = factorial(primeCount);
        BigInteger factorial2 = factorial(n - primeCount);
        return factorial1.multiply(factorial2).mod(BigInteger.valueOf(mod)).intValue();
    }

    public int countPrimes(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, 2, n + 1, true);
        for (int i = 2; i * i <= n; i++) {
            if (prime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    prime[j] = false;
                }
            }
        }
        return (int) IntStream.range(0, prime.length).filter(i -> prime[i]).count();
    }

    int mod = 1000000007;

    public BigInteger factorial(int n) {
        BigInteger fac = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            fac = fac.multiply(BigInteger.valueOf(i));
        }
        return fac.mod(BigInteger.valueOf(mod));
    }
}
