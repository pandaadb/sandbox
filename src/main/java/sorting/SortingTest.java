package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SortingTest {

	
	public static void main(String[] args) {
		List<Integer> t1 = new ArrayList<>();
		List<Integer> t2 = new ArrayList<>();
		List<Integer> t3 = new ArrayList<>();
		for(int i = 0; i< 100_000; i++) {
			int tmp = new Random().nextInt();
			t1.add(tmp);
			t2.add(tmp);
			t3.add(tmp);
		}
		
		long start = System.currentTimeMillis();
		t1.sort(null); // equivalent to Collections.sort()
		System.out.println("T1 Took: " + (System.currentTimeMillis() - start));
		
		start = System.currentTimeMillis();
		List<Integer> sortedT2 = t2.stream().sorted().collect(Collectors.toList());
		System.out.println("T2 Took: " + (System.currentTimeMillis() - start));
		
		start = System.currentTimeMillis();
		List<Integer> sortedT3 = t3.parallelStream().sorted().collect(Collectors.toList());
		System.out.println("T3 Took: " + (System.currentTimeMillis() - start));
//		
		List<Integer> test = Arrays.asList(4,2,1,3);
		test.sort((i1, i2) -> i1.compareTo(i2));
		test.forEach(System.out::println);
	}
	
	
	public static <T extends Comparable<? super T>> void saveSort(final List<T> myList) {
		if(myList != null) {
			myList.sort(null);
		}
	}
	
	public static <T> void saveSort(final List<T> myList, Comparator<T> comparator) {
		if(myList != null) {
			myList.sort(comparator);
		}
	}
	
}
