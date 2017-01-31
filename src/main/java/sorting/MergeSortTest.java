package sorting;

import java.util.Arrays;

public class MergeSortTest {

	
	public static void main(String[] args) {
		
		int[] a2 = {3,1,2,4,21231,1345646,3232233,7,3,1,0,333,2};
		printSorted(m2(a2));
		
		mergeSort(a2, 0, a2.length - 1);
		printSorted(a2);
	}
	
	static int[] m2(final int[] a) {
		int startIndex = 0;
		int endIndex = a.length - 1;
		if(endIndex - startIndex + 1 <= 1) {
			return a;
		}
		
		int midIndex = startIndex + (endIndex - startIndex ) / 2;
		int[] left = Arrays.copyOfRange(a, startIndex, midIndex +1);      // from start to midIndex
		int[] right = Arrays.copyOfRange(a, midIndex + 1, endIndex + 1);  // from midIndex to EndIndex
		
		if(left.length + right.length != a.length) {
			System.err.println("Length mismatch");
		}
		
		int[] mergedLeft = m2(left);
		int[] mergedRight = m2(right);
		
		return merge(mergedLeft, mergedRight);
	}
	
	static int[] merge(int[] left, int[] right) {
		int[] tmp = new int[left.length + right.length];
		int idx = 0;
		int leftIdx = 0;
		int rightIdx = 0;
		
		while( leftIdx < left.length && rightIdx < right.length) {
			int l = left[leftIdx];
			int r = right[rightIdx];
			
			if(l > r) {
				tmp[idx] = r;
				rightIdx++;
			} else {
				tmp[idx] = l;
				leftIdx++;
			}
			
			idx++;
		}
		
		while(leftIdx < left.length) {
			tmp[idx] = left[leftIdx];
			idx++;
			leftIdx++;
		}
		
		while(rightIdx < right.length) {
			tmp[idx] = right[rightIdx];
			idx++;
			rightIdx++;
		} 
		
		return tmp;
	}
	
	public static void printSorted(int[] a) {
		System.out.println("----");
		for(int x : a) System.out.print(x + " ");
		System.out.println("\n----");
	}
	
	public static void mergeSort(int[] a, final int startIndex, final int endIndex){ 
		// if there is only 1 element left, then we return because we are sorted
		if(endIndex - startIndex + 1 <= 1) {
			return; 
		}
		int midIndex = startIndex + (endIndex - startIndex ) / 2;
		// sort the left partial array
		mergeSort(a, startIndex, midIndex);
		// sort the right partial array
		mergeSort(a, midIndex + 1, endIndex);
		//merge the 2 arrays
		inPlaceMerge(a, startIndex, midIndex, endIndex);
	}

	// weird
	static void inPlaceMerge(int[] a, int startIndex, int midIndex, int endIndex) {
		int leftIdx = startIndex;
		int rightIdx = midIndex + 1;
		int idx = startIndex;
		
		int midEnd = midIndex;
		
		while(leftIdx <= midEnd && rightIdx <= endIndex) {
			int l = a[leftIdx];
			int r = a[rightIdx];

			if( l > r ) {
				// swap em: 
				a[idx] = r;
				a[rightIdx] = l;
				midEnd ++;
				rightIdx ++;
				leftIdx ++;
			} else {
				a[idx] = l;
				leftIdx ++;
			}
			idx ++;
		}
		
		while(leftIdx <= midIndex) {
			a[idx] = a[leftIdx];
			leftIdx++; idx++;
		}
		
		while(rightIdx <= endIndex) {
			a[idx] = a[rightIdx];
			rightIdx++; idx++;
		}
	}
}
