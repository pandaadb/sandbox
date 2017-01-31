package dw.stream;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.tuple.Pair;

public class StreamMapper {


	public static void main(String[] args) {
		DoubleIterable<Integer> doubleIter = new DoubleIterable<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		for (Pair<Integer, Integer> p : doubleIter) {
			System.out.println(p);
		}
	}
	
	public static class DoubleIterable<T> implements Iterable<Pair<T, T>> {

		private List<T> elements;

		public DoubleIterable(List<T> elements) {
			if( (elements.size() & 1) != 0) {
				throw new IllegalArgumentException("Not an even number of elements");
			}
			this.elements = elements;
		}
		
		@Override
		public Iterator<Pair<T,T>> iterator() {
			return new Iterator<Pair<T,T>>() {
				int index = 0;
				
				@Override
				public boolean hasNext() {
					return index + 1 < elements.size();
				}

				@Override
				public Pair<T, T> next() {
					if(!hasNext()) {
						throw new NoSuchElementException();
					}
					Pair<T, T> tmp = Pair.of(elements.get(index), elements.get(index + 1));
					index += 2;
					return tmp;
				}
			};
		}
		
	}
	
}
