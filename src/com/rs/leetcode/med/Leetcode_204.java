package com.rs.leetcode.med;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Leetcode_204 {
	/*
	 * Given an integer n, return the number of prime numbers that are strictly less
	 * than n.
	 * 
	 * 
	 * 
	 * Example 1:
	 * 
	 * Input: n = 10 Output: 4 Explanation: There are 4 prime numbers less than 10,
	 * they are 2, 3, 5, 7. Example 2:
	 * 
	 * Input: n = 0 Output: 0 Example 3:
	 * 
	 * Input: n = 1 Output: 0
	 * 
	 * 
	 * Constraints:
	 * 
	 * 0 <= n <= 5 * 106
	 */
	public int countPrimes(int n) {
//		System.out.println(n);
		if (n < 2) {
			return 0;
		}
		int count = 0;
		for (int i = 2; i < n; i++) {
//			System.out.println(isPrime(i)+" "+i);
			if (isPrime(i)) {
//				System.out.println(i);
				count++;
			}
		}
		return count;
	}

	// Brute force -> O(n⋅root (n)​)
	public boolean isPrime(int n) {
		// for prime we can consider the number can be divisible by 2 and go on upto
		// square root to the number.
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

//	Sieve of Eratosthenes

	public int sieveOfEratosthenes_prime_count(int n) { //Time Limit Exceeded

		if (n < 2) {
			return 0;
		}
		int count = 0;
		// initial all are prime - consider
		Map<Integer, Boolean> primeMap = new HashMap<>();
		for (int i = 2; i <= n; i++) {
			primeMap.put(i, true);
		}
		// sieve
		for (int i = 2; i < n; i++) {
			if (primeMap.get(i)) {
				count++;
				for (int j = i * 2; j < n; j = j + i) {
					primeMap.put(j, false);
				}
			}
		}
		return count;
	}
	public int sieveOfEratosthenes_prime_count_1(int n) { 


		int count = 0;
		// initial all are prime - consider
		boolean[] isPrime= new boolean[n];
		for (int i = 2; i < n; i++) {
			isPrime[i]=true;
		}
		// sieve
		for (int i = 2; i < n; i++) {
			if (isPrime[i]) {
				count++;
				for (int j = i * 2; j < n; j = j + i) {
					isPrime[j]=false;
				}
			}
		}
		return count;
	}
}
