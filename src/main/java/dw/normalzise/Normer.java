package dw.normalzise;

import java.text.Normalizer;

public class Normer {

	public static void main(String[] args) {
		String x = "ㅇㅞᆹ";

		String t2 = Normalizer.normalize(x, Normalizer.Form.NFKC);
		System.out.println(x);
		System.out.println(t2);
		
		char test[] = {'ㅄ'};
	    System.out.println((int)'ㅄ');
	    System.out.println((int)test[0]);
		
	}

	public static void normalize() {
		
	}
}
