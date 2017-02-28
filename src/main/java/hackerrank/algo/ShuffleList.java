package hackerrank.algo;

import java.util.Arrays;
import java.util.Random;

public class ShuffleList {

	public static void main(String[] args) {

		int[] a = new int[] { 3, 4, 5, 6, 2, 1, 2, 3, 5, 6, 7, 43, 2, 31, 2, 3, 5, 2, 123, 23, 52, 1, 233 };
		print(a);
		randomize(a);
		print(a);
		randomize(a);
		print(a);
	}
	

	public static void randomize(int[] a) {
		Random r = new Random();

		for (int i = 0; i < a.length; i++) {
			int tmp = a[i];
			int randomIdx = r.nextInt(a.length -i) + i;
			a[i] = a[randomIdx];
			a[randomIdx] = tmp;
		}
	}

	public static void print(int[] a) {
		Arrays.stream(a).forEach(i -> System.out.print(i + " | "));
		System.out.println();
	}

}
