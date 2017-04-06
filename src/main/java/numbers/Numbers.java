package numbers;

public class Numbers {

	public static void main(String[] args) {
		
		double x = 1 / 3; 
		System.out.println(x);
		System.out.println((int)x);
		
		x = 1.0 / 3; 
		System.out.println(x);
		System.out.println((int)x);
		
		x = 1 / 3.0; 
		System.out.println(x);
		System.out.println((int)x);
		
		x = 1.0 / 3.0; 
		System.out.println(x);
		System.out.println((int)x);
		
		x = 1 / (double) 3; 
		System.out.println(x);
		System.out.println((int)x);
		
		x = (double) 1 / 3; 
		System.out.println(x);
		System.out.println((int)x);
		
		System.out.println( (int) 0.9999);
		System.out.println( (int) -0.01);
	}
}
