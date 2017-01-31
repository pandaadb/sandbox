package test.guice;

public class MergeInherit {
	
	public static void main(String[] args) {
		int[] t = {2,5,2,1,4,7,5,3,2,23,121,2,0};
		print(t, 0, t.length - 1);
		mergeSort(t, 0, t.length - 1);
		print(t, 0, t.length - 1);
	}
	
	
	public static void mergeSort(final int[] array, int start, int end) {
		if(start >= end) {
			return;
		}
		// half the stuff
		int half = (end - start) / 2;
		
		// merge left half 
		mergeSort(array, start, start + half);
		// merge right half
		mergeSort(array, start + half + 1, end);
		
		System.out.println();
		merge(array, start, start + half, end);
	}
	
	static void merge(int[] a, int start, int mid, int end) {
//		System.out.println("Merge: " + start + " " + mid + " " + end);
		int i = start;
		int j = mid + 1;
		while(i <= mid && j <= end) {
			int left = a[i];
			int right = a[j];
			if(left > right) {
				int tmp = a[i];
				a[i] = a[j];
				a[j] = tmp;
				j ++;
			} else {
				i++;
			}
		}
//		print(a, 0, a.length - 1);
	}
	
	public static void print(int[] array, int start, int end) {
		for(int i=start; i<=end; i++) {
			System.out.print(array[i]+ " ");
		}
		System.out.println();
	}
}
