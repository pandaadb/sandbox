package hackerrank;

public class Subarray {

	
	public static void main(String[] args) {
		System.out.println(maxLength(new int[] {3,1,2,1}, 4));
	}
	
	static int maxLength(int[] a, int k) {
		int max = 1;
		
		for(int i =0; i < a.length; i ++) {
			int tmpMax = 1;
			int sum = a[i];
			for(int j = i+1; j < a.length; j++) {
				sum += a[j];
				if(sum > k) {
					break;
				}
				tmpMax++;
			}
			if(tmpMax > max) {
				max = tmpMax;
			}
		}
		
		return max;
    }
}
