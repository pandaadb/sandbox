package hackerrank.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BinaryPrefixSearch {
	
	public static void main(String[] args) {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		Random r = new Random();
		
		long start = System.currentTimeMillis();
		
		List<String> strings = new ArrayList<>(50000000);
		
		for(int i = 0; i < 10000000; i++) {
			StringBuffer b = new StringBuffer();
			for(int j = 0; j < 20 ; j++) {
				b.append(chars[r.nextInt(chars.length)]);
			}
			strings .add(b.toString());
		}
		
		strings.add("artur");
		strings.add("artur2");
		strings.add("aratur");
		
		Collections.sort(strings);
		
		System.out.println("Index and Sort took: " + (System.currentTimeMillis() - start));
		
		System.out.println(String.join(",", getCommonPrefix("art", strings)));
		
		System.out.println("Found strings: " + getCommonPrefix("art", strings).size());
		System.out.println("Found strings: " + getCommonPrefix("artur2", strings).size());
		System.out.println("Found strings: " + getCommonPrefix("asd", strings).size());
		System.out.println("Found strings: " + getCommonPrefix("nnb", strings).size());
		System.out.println("Found strings: " + getCommonPrefix("asda", strings).size());
		System.out.println("Found strings: " + getCommonPrefix("c", strings).size());
	}
	
	
	static Set<String> getCommonPrefix(final String prefix, final List<String> input) {
	
		long start = System.nanoTime();
		
		int index = Collections.binarySearch(input, prefix, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				// o1 being the prefix
				if(o1.startsWith(o2)) return 0;
				return o1.compareTo(o2);
			}
		});
		
		if(index < 0) {
			return Collections.emptySet();
		}
		
		Set<String> res = new HashSet<>();
		res.add(input.get(index));
		
		boolean keepSearching = true;
		int tmp = index - 1;
		while(keepSearching && tmp > 0) {
			if(input.get(tmp).startsWith(prefix)) {
				res.add(input.get(tmp));
			} else {
				keepSearching = false;
			}
			tmp--;
		}
		
		keepSearching = true;
		tmp = index + 1;
		while(keepSearching && tmp < input.size()) {
			if(input.get(tmp).startsWith(prefix)) {
				res.add(input.get(tmp));
			} else {
				keepSearching = false;
			}
			tmp++;
		}
		
		System.out.println("Search for '" + prefix + "' took: " + (System.nanoTime() - start) + " ns");
		
		return res;
	}
}
