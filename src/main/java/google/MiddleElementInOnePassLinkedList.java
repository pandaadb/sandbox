package google;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class MiddleElementInOnePassLinkedList {

	public static void main(String[] args) {
		System.out.println(mid(new LinkedList<>(Arrays.asList(1,2,3,4,5)))); // 3
		System.out.println(mid(new LinkedList<>(Arrays.asList(1,2,3,4,5,6, 7)))); // 4
		
	}
	
	static int mid(LinkedList<Integer> list) {
		Integer pointer = null;
		int counter = 0;
		Iterator<Integer> iterator = list.iterator();
		Iterator<Integer> midPointer = list.iterator();
		while(iterator.hasNext()) {
			Integer node = iterator.next();
			counter ++;
			if(counter % 2 == 1) {
				pointer = midPointer.next();
			}
		}
		return pointer;
	}
}
