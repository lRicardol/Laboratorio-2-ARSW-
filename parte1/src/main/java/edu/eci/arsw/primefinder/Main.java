package edu.eci.arsw.primefinder;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<PrimeFinderThread> threads = new ArrayList<>();
		Object lockObject = new Object();
		boolean anyThreadRunning = true;

		threads.add( new PrimeFinderThread(0,10000000,1,lockObject));
		threads.add( new PrimeFinderThread(10000001,20000000,2,lockObject));
		threads.add( new PrimeFinderThread(20000001,30000000,3,lockObject));
		for (PrimeFinderThread	thread : threads) {
			thread.start();
		}
		

	}

	private static void getTotalPrimes(List<PrimeFinderThread> threads){
		int total_primes_find = 0;
		for(PrimeFinderThread thread : threads){
			total_primes_find += thread.getPrimes().size();
		}
		System.out.println("Primes finded: " + total_primes_find);
	}
}
