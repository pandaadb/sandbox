package dw.stream;

import java.util.Random;

public class ModTest {

	
	public static void main(String[] args) {
		Random r = new Random();
		
		long start = System.currentTimeMillis();
		for(int i =0; i < 10000000; i ++) {
			if(r.nextInt() % 2 == 0){
				doSmth();
			}
		}
		System.out.println(System.currentTimeMillis() - start);
		
		
		start = System.currentTimeMillis();
		for(int i =0; i < 10000000; i ++) {
			if( (r.nextInt() & 2) == 0){
				doSmth();
			}
		}
		System.out.println(System.currentTimeMillis() - start);
	}
	
	public static void doSmth() {
		
	}
}
