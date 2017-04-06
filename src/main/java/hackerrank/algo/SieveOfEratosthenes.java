package hackerrank.algo;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

public class SieveOfEratosthenes {

	public static void main(String[] args) {
		Stopwatch w = Stopwatch.createStarted();
		sieve(100_000_000);
		w.stop();
		System.out.println(w.elapsed(TimeUnit.MILLISECONDS));
	}
	
	public static void sieve(int n) {
		int[] tmp = new int[n];
		int p = 2; // first prime
		int count = 1;
		while( p < n - 1 ) {
			count++;
			int tmpP = p;
			while(tmpP < n) {
				tmp[tmpP] = 1;
				tmpP +=p;
			}
			
			boolean searching = true;
			while(searching && p < n - 1) {
				if((tmp[++p]) == 0) searching = false;
			}
		}
		System.out.println(count);
	}
}
