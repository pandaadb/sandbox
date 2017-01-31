package google;

import java.util.stream.IntStream;

public class Duplicatenumber {

	public static void main(String[] args) {
		
		int[] array = IntStream.rangeClosed(0,10000).toArray();
		
//		int[] array = new int[] {0,1,1,2,3,5}; 
		
		array[888] = 12;
		
		findDup(array);
		
	}
	
	static int findDup(int[] array) {
		long sum = 0;
		long sqSum = 0;
		for(long e : array) {
			sum += e;
			sqSum += (e * e);
		}
		long n = array.length;
		long a = ((n * (n  - 1 )) / 2) - sum;
		long tmpB = ((n * (n - 1) * (2 * n - 1))) / 6;
		long b = (tmpB - sqSum) / a ;
		long removed = ( b + a) / 2;
		long dup = (b -a) / 2;
		System.out.printf("Removed: %s, Duplicate: %s", removed, dup);
		return 0;
	}
}
