package edu.eci.arsw.primefinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
	private static Scanner scanner = new Scanner(System.in);

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

		try {
			while (true) {
				anyThreadRunning = false;
				for (PrimeFinderThread t : threads){
					if(t.isAlive()) {
						anyThreadRunning = true;
						break;
					}
				}
				if(!anyThreadRunning){
					break;
				}
				Thread.sleep(5000);
				stopAndExecuteThreads(threads, lockObject);
			}
			System.out.println("All threads have finished. Program terminated.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Stops threads after 5s of execution and resume when user press enter
	 * @param threads
	 * @param lockObject
	 * @throws InterruptedException
	 */
	private static void stopAndExecuteThreads(List<PrimeFinderThread> threads, Object lockObject) throws InterruptedException {
		for (PrimeFinderThread t : threads) {
			t.setExecution(false);
		}
		Thread.sleep(500);
		System.out.println("Stopping threads...");
		getTotalPrimes(threads);
		System.out.println("Press enter to resume threads");
		scanner.nextLine();
		System.out.println("Resuming threads...");
		for(PrimeFinderThread t : threads) {
			t.setExecution(true);
			synchronized (lockObject) {
				lockObject.notifyAll();
			}
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
