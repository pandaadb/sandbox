package search;

import java.util.Arrays;

public class BinarySearch {

	
	public static void main(String[] args) {
		int[] test = new int[] { 1,45,2,4,6,7,4,2,2,4,56,7,8,4,2,32,4,53,46,7,75,33,42,24,32,234,45353,53,523,3};
		Arrays.sort(test);
		
		System.out.println(search(test,54, 0, test.length -1));
	} 
	
	static int search(int[] a, int toSearch, int start, int end) {
		int mid = start + ((end - start) / 2);
		int tmp = a[mid];
		
		if(mid == start || tmp == toSearch) {
			return tmp == toSearch? toSearch : -1;
		} else if( tmp < toSearch) {
			return	search(a, toSearch, mid + 1, end);
		} else {
			return search(a, toSearch, start, mid -1);
		}
	}

}
