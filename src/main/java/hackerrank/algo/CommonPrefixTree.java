package hackerrank.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CommonPrefixTree {

	public static void main(String[] args) {
		Node treeRoot = new Node();
		
		index("Artur", treeRoot);
		index("ArturTestMe", treeRoot);
		index("Blop", treeRoot);
		index("Muha", treeRoot);
		index("ArtIsCool", treeRoot);
		
		// fill the tree with some thousand arbitrary Strings of lenght 20 
		
		List<String> strings = new ArrayList<>();
		
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		Random r = new Random();
		
		long start = System.currentTimeMillis();
		for(int i = 0; i < 5000000; i++) {
			StringBuffer b = new StringBuffer();
			for(int j = 0; j < 20 ; j++) {
				b.append(chars[r.nextInt(chars.length)]);
			}
			strings.add(b.toString());
//			index(UUID.randomUUID().toString(), treeRoot);
		}
		
		System.out.println("Index took: " + (System.currentTimeMillis() - start));
		
		strings.add("art");
		strings.add("a");
		strings.add("artu");
		strings.add("arturt");
		strings.add("b");
		
		System.out.println(" ----- Tree search -----");
		find("art", treeRoot);
		find("a", treeRoot);
		find("artu", treeRoot);
		find("arturT", treeRoot);
		find("b", treeRoot);
//		System.out.println("Prefix: art - " + String.join(",", find("art", treeRoot)));
//		System.out.println("Prefix: a - " + String.join(",", find("a", treeRoot)));
//		System.out.println("Prefix: artu - " + String.join(",", find("artu", treeRoot)));
//		System.out.println("Prefix: arturt - " + String.join(",", find("arturT", treeRoot)));
//		System.out.println("Prefix: b - " + String.join(",", find("b", treeRoot)));
		
		// The analog test for searching in a list
		
		System.out.println(" ----- List search -----");
		findInList("art", strings);
		findInList("a", strings);
		findInList("artu", strings);
		findInList("arturt", strings);
		findInList("b", strings);
		
		findInList("asdfsas", strings);
		findInList("asdfagafsa", strings);
		
//		System.out.println("Prefix: art - " + String.join(",", findInList("art", strings)));
//		System.out.println("Prefix: a - " + String.join(",", findInList("a", strings)));
//		System.out.println("Prefix: artu - " + String.join(",", findInList("artu", strings)));
//		System.out.println("Prefix: arturt - " + String.join(",", findInList("arturT", strings)));
//		System.out.println("Prefix: b - " + String.join(",", findInList("b", strings)));
		
		Collections.sort(strings);
		
		System.out.println(" ----- Binary search -----");

		findInListBinary("art", strings);
		findInListBinary("a", strings);
		findInListBinary("artu", strings);
		findInListBinary("arturt", strings);
		findInListBinary("b", strings);
		
		findInListBinary("asdfsas", strings);
		findInListBinary("asdfagafsa", strings);
		
//		System.out.println("Prefix: art - " + String.join(",", findInListBinary("art", strings)));
//		System.out.println("Prefix: a - " + String.join(",", findInListBinary("a", strings)));
//		System.out.println("Prefix: artu - " + String.join(",", findInListBinary("artu", strings)));
//		System.out.println("Prefix: arturt - " + String.join(",", findInListBinary("arturT", strings)));
//		System.out.println("Prefix: b - " + String.join(",", findInListBinary("b", strings)));
	}
	
	static class Node {
		
		Map<Integer, Node> mapping = new HashMap<>();
		
		Set<String> words = new HashSet<>();
		
		void add(String word) {
			words.add(word);
		}
		
		boolean contains(String word) {
			return words.contains(word);
		}
		
	}
	
	static List<String> findInList(String prefix, List<String> options) {
		List<String> res = new ArrayList<>();
		long start = System.currentTimeMillis();
		for(String s : options) {
			if(s.startsWith(prefix)) res.add(s);
		}
		
		System.out.println("Search took: " + (System.currentTimeMillis() - start));
		return res;
	}
	
	static Set<String> findInListBinary(String prefix, List<String> options) {
		Set<String> res = new HashSet<>();
		long start = System.currentTimeMillis();
		int lower = 0;
		int upper = options.size()-1;
		int mid = 0;
		
		// find lower index of the first letter
		char toLookFor = prefix.charAt(0);
		while(lower < upper) {
			
			mid = lower + (upper - lower) / 2;
			
			
			char test = options.get(mid).charAt(0);
			if(test == toLookFor) {
				break;
			}
			
			if(test > toLookFor) {
				// search left
				upper = mid;
			} else {
				lower = mid;
			}
		}
		
		// go left and collect
		int tmp = mid;
		boolean keepSearch = true;
		boolean firstFound = false;
		while(keepSearch && tmp > 0) {
			
			if(options.get(tmp).startsWith(prefix)) {
				res.add(options.get(tmp));
				if(!firstFound) firstFound = true;
				
			} else {
				if(firstFound || options.get(tmp).charAt(0) != toLookFor) keepSearch = false;
			}
			
			tmp --;
			
		}
		
		tmp = mid;
		keepSearch = true;
		firstFound = false;
		while(keepSearch && tmp < options.size()) {
			
			if(options.get(tmp).startsWith(prefix)) {
				res.add(options.get(tmp));
				if(!firstFound) firstFound = true;
				
			} else {
				if(firstFound || options.get(tmp).charAt(0) != toLookFor) keepSearch = false;
			}
			
			tmp ++;
		}
		
		System.out.println("Search took: " + (System.currentTimeMillis() - start));
		return res;
	}
	
	static void index(final String toIndex, final Node root) {
		Node tmp = root;
		// indexing takes O(n)
		long start = System.currentTimeMillis();
		for(char c : toIndex.toLowerCase().toCharArray()) {
			int val = (int) (c - 'a');
			tmp.add(toIndex);
			tmp = tmp.mapping.computeIfAbsent(val, i-> new Node());
			if(tmp.contains(toIndex)) break; // stop, we have seen the word before
		}
		long timeSpent = System.currentTimeMillis() - start;
		if(timeSpent > 10) {
			System.out.println("Indexing took: " + timeSpent);
		}
	}

	static Set<String> find(String prefix, final Node root) {
		
		long start = System.currentTimeMillis();
		
		Node tmp = root;
		// step down the tree to all common prefixes, O(n) where prefix defines n
		for(char c : prefix.toLowerCase().toCharArray()) {
			int val = (int) (c - 'a');
			if(!tmp.mapping.containsKey(val)) {
				return Collections.emptySet();
			}
			else tmp = tmp.mapping.get(val);
		}
		
		System.out.println("Search took: " + (System.currentTimeMillis() - start));
		return tmp.words;
	}
}
