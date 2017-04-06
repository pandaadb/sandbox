package hackerrank.algo;

public class QuickSort {

	public static void main(String[] args) {
		
//		int[] a = new int[] {8,9,3,2,1,5,4,0,23,28,23,28,23,1,2,4,5};
		int[] a = new int[] {0,0};
		
		quickSort(a , 0, a.length - 1);
		print(a);
	}
	
	static void print(int[] a) {
		for(int i = 0; i < a.length ; i ++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	static void quickSort(int []array, int left, int right) {
		// pick the pivot 
		if(left >= right) return;
		int pivot = array[left + ((right - left) / 2)];
		
		int tmpLeft = left;
		int tmpRight = right;
		
		
		while(true) {
			
			while(array[tmpLeft] < pivot) {
				tmpLeft++;
			}
			
			while(array[tmpRight] > pivot) {
				tmpRight --;
			}
			
			if(tmpLeft >= tmpRight) break;
			
			int tmp = array[tmpLeft];
			array[tmpLeft] = array[tmpRight];
			array[tmpRight] = tmp;
			tmpLeft++;
			tmpRight--;
		}
		
		// recursively do it left and right
		quickSort(array, left, tmpRight - 1);
		quickSort(array, tmpRight + 1, right);
	}
}
