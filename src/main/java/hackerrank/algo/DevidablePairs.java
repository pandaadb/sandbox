package hackerrank.algo;

public class DevidablePairs {

	public static void main(String[] args) {
		x_better(6, 3, new int[] {1,3,2,6,4,5,9});
	}
	
	public static void x(int n, int k, int[] a) {
		int count = 0;
		for(int i = 0; i < a.length - 1; i ++) {
			for( int j = i +1; j < a.length; j++) {
				if( (a[i] + a[j]) % k == 0 ) count++;
			}
		}
		System.out.println(count);
	}
	
	public static void x_better(int n, int k, int[] a) {
		int[] buckets = new int[k];
		for(int i =0; i< a.length; i++) {
			int j = a[i] % k;
			buckets[j]++;
		}
		
		int sum = (buckets[0] * (buckets[0] -1)) / 2;
		
		for(int i =1; i <= buckets.length / 2 && i!=k-i ; i++) {
			sum += (buckets[i] * buckets[(k - i) % k]);
		}

		if(k%2==0) {
	        sum+=(buckets[k/2]*(buckets[k/2]-1))/2;
		}
		
		System.out.println(sum);
	}
}
