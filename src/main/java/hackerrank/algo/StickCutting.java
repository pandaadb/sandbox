package hackerrank.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StickCutting {

	
	public static void main(String[] args) {
		
		cut(new int[] {5,4,4,2,2,8});
	}
	
	public static void cut(int[] sticks) {

		List<Integer> filterable = new ArrayList<>();
		IntStream.of(sticks).forEach(filterable::add);
		List<Integer> sorted = filterable.stream().sorted().collect(Collectors.toList());
		
		while(!sorted.isEmpty()) {
			int oldSize = sorted.size();
			Integer cut = sorted.get(0);
			sorted = sorted.stream().map(i -> i.intValue() - cut).filter( i -> i > 0).collect(Collectors.toList());
			System.out.println(oldSize);
		}
	}
}
