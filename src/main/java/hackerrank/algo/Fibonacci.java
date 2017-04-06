package hackerrank.algo;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

	public static Map<Integer, Integer> map = new HashMap<>();
	
	public static void main(String[] args) {
		for(int i=0; i< 10; i++){
			long start = System.nanoTime();
			int rekursive = rekursive(30);
			System.out.println(rekursive + " Took: " + (System.nanoTime() - start));
		}
		
		for(int i=0; i< 10; i++){
			long start = System.nanoTime();
			int rekursive = rekursiveMap(30);
			System.out.println(rekursive + " Took: " + (System.nanoTime() - start));
		}
	}
	
	
	public static long iterative(int n){
		long a = 0;
		long b = 1;
		for(int i = 1; i < n; i ++) {
			long tmp = a + b;
			a = b;
			b = tmp;
		}
		return b;
	}
	
	public static int rekursive(int n) {
		if( n <= 2 ) return 1;
		return rekursive(n-2) + rekursive(n-1);
	}
	
	public static int rekursiveMap(int n) {
		if( n <= 2 ) return 1;
		return map.compute(n-2, (x,y) -> rekursiveMap(n-2)) + map.compute(n-1, (x,y) -> rekursiveMap(n-1));
	}
}
