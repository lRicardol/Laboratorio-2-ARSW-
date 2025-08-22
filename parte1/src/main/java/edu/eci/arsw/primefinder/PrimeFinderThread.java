package edu.eci.arsw.primefinder;

import java.util.LinkedList;
import java.util.List;

public class PrimeFinderThread extends Thread{

	
	private int a,b,t;
	
	private List<Integer> primes=new LinkedList<Integer>();
	private boolean execution;
	private Object lockObject;
	public PrimeFinderThread(int a, int b, int t) {
		this.a = a;
		this.b = b;
		this.t = t;
	}


	public void run(){
		for (int i=a;i<=b;i++){						
			if (isPrime(i)){
				primes.add(i);
				System.out.println("Thread " + t + " found prime:" + i);
			}
		}
	}
	
	boolean isPrime(int n) {
	    if (n%2==0) return false;
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}

	public List<Integer> getPrimes() {
		return primes;
	}
	
}
