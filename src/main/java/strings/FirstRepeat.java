package strings;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

public class FirstRepeat {

	public static void main(String[] args) {

		Pair<Optional<Character>, Optional<Character>> collect = "asdasdasdbsdsvasv".chars().mapToObj(c -> (char) c)
				.collect(PairCollector.get());
		collect.getLeft().ifPresent(c -> System.out.println(c));
		collect.getRight().ifPresent(c -> System.out.println(c));
		
		System.out.println();
		List<Character> toTest = "asdasdasdbsdsvasv".chars().mapToObj(c -> (char) c).collect(Collectors.toList());
		
		Pair<Optional<Character>,Optional<Character>> collect2 = toTest.parallelStream().collect(PairCollector.get());
		collect2.getLeft().ifPresent(c -> System.out.println(c));
		collect2.getRight().ifPresent(c -> System.out.println(c));
	}

}
