package edu.eci.arsw.primefinder;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<PrimeFinderThread> threads = new ArrayList<>();
		Object lockObject = new Object();

		threads.add( new PrimeFinderThread(0,10000000,1));
		threads.add( new PrimeFinderThread(10000001,20000000,2));
		threads.add( new PrimeFinderThread(20000001,30000000,3));
		for (PrimeFinderThread	thread : threads) {
			thread.start();
		}

	}
	
}
