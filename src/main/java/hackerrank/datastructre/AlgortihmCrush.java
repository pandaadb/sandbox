package hackerrank.datastructre;

import java.util.Scanner;

public class AlgortihmCrush {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int listSize = s.nextInt();
		int opsSize = s.nextInt();

		long[] tmpK = new long[listSize];

		for (int i = 0; i < opsSize; i++) {
			int a = s.nextInt() - 1;
			int b = s.nextInt() - 1;
			int k = s.nextInt();
			tmpK[a] += k;
			if(b + 1 < listSize) {
				tmpK[b + 1] -= k;
			}
		}

		long max = 0;
		long tmp = 0;
		for (int i = 0; i < listSize; i++) {
			tmp += tmpK[i];
			if(tmp > max) max = tmp;
		}

		System.out.println(max);
	}
}
