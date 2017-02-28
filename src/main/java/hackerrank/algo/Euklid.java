package hackerrank.algo;

public class Euklid {

	
	public static void main(String[] args) {
		System.out.println(gcd(1071, 462));
	}
	
	 public static int gcd(int p, int q) {
         if (q == 0) {
                 return p;
         }
         return gcd(q, p % q);
 }
}
