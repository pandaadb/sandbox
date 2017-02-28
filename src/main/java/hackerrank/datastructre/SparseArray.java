package hackerrank.datastructre;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SparseArray {

	public static void main(String[] args) {
		try(Scanner s = new Scanner(System.in)) {
			int inputSize = s.nextInt();
			Map<String, Integer> storage = new HashMap<>(inputSize);
			for(int i =0; i < inputSize; i++) {
				storage.compute(s.next(), (k,v) -> v == null ? 1 : v+1);
			}
			int checkSize = s.nextInt();
			for(int i=0; i < checkSize; i++) {
				System.out.println(storage.getOrDefault(s.next(), 0));
			}
		}
	}
}
