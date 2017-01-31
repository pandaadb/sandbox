package maths;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Math {

	public static void main(String[] args) {
		Set<Integer> toTest = new HashSet<>(Arrays.asList(1,2,3,4,6));
		
		System.out.println(toTest.contains(1 * 2 * 4));
		
	}
	
}
