package google;

public class SortedCircularArrayTest {

	public static void main(String[] args) {
		int[] t1 = { 9, 10, 14, 1, 2 };
		int[] t2 = { 9, 10, 14, 1, 2, 3 };
		findLargest(t1);
		findLargest(t2);
	}
	
	static int findLargest(int[] array) {
		search(array, 0, array.length -1, Integer.MIN_VALUE);
		return 0;
	}
	
	static int search(int[] array, int start, int end, int old) {
		int mid = start + ((end-start) / 2);
		int tmp = array[mid];
		System.out.println("Mid val: " + tmp + " Index: " + mid);
		return 0;
	}
	
}
