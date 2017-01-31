package hackerrank.algo;

public class Kangoroos {

	public static void main(String[] args) {
		test2(0, 2, 5, 3);
		test2(43, 2, 70, 2);
	}
	
	public static void test(int x1, int v1, int x2, int v2) {
		boolean testX1 = x1 > x2 ? false : true;
		
		while(true) {
			if(x1 == x2) {
				System.out.println("YES");
				return;
			} 
			if(testX1) {
				if( x1 > x2) {
					System.out.println("No");
					return;
				}
			} else {
				if( x2 > x1) {
					System.out.println("No");
					return;
				}
			}
			
			x1 += v1;
			x2 += v2;
		}
		
	}
	
	public static void test2(int x1, int v1, int x2, int v2) {
		if(v2 >= v1) {
			System.out.println( "NO");
			return;
		}
		double val = (x1 - x2) / (v2 - v1);
		
		boolean t = ((x1 - x2) % (v2 - v1) == 0);
		System.out.println(t && val > 0 ? "YES" : "NO");
	}
	
	
	
}
