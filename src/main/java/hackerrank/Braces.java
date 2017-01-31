package hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Braces {

	public static void main(String[] args) {
		String[] test = new String[] { ")"};
		braces(test);
		for (String x : test)
			System.out.println(x);
	}

	static String[] braces2(String[] values) {
		Map<Character, Integer> charMap = new HashMap<>();
		for (int i = 0; i < values.length; i++) {
			String test = values[i];
			boolean matched = true;
			for (int y = 0; y < test.toCharArray().length && matched; y++) {
				char c = test.charAt(y);
				switch (c) {
				case '[':
				case '(':
				case '{':
					increment(charMap, c);
					break;
				case ']':
					matched = incrementAndCheck(charMap, c, '[');
					break;
				case ')':
					matched = incrementAndCheck(charMap, c, '(');
					break;
				case '}':
					matched = incrementAndCheck(charMap, c, '{');
					break;
				default:

				}
			}

			values[i] = matched ? "YES" : "NO";
		}
		return values;
	}

	static void increment(final Map<Character, Integer> map, final char c) {
		Integer val = map.computeIfAbsent(c, (key) -> 0);
		map.put(c, val.intValue() + 1);
	}

	static boolean incrementAndCheck(final Map<Character, Integer> map, final char c, final char toCheck) {
		int charCounter = map.computeIfAbsent(c, (key) -> 0);
		int checkCounter = map.computeIfAbsent(toCheck, (key) -> 0);
		if (checkCounter <= charCounter) {
			return false;
		}
		increment(map, c);
		return true;
	}

	static String[] braces(String[] values) {
		Stack<Character> characterStack = new Stack<>();
		for (int i = 0; i < values.length; i++) {
			String test = values[i];
			boolean matched = true;
			if (test.length() % 2 != 0) {
				matched = false;
			}
			for (int y = 0; y < test.toCharArray().length && matched; y++) {
				char c = test.charAt(y);
				switch (c) {
				case '[':
				case '(':
				case '{':
					characterStack.push(c);
					break;
				case ']':
					matched = pop(characterStack, '[');
					break;
				case ')':
					matched = pop(characterStack, '(');
					break;
				case '}':
					matched = pop(characterStack, '{');
					break;
				default:

				}
			}
			if(matched) {
				matched = characterStack.isEmpty();
			}
			
			values[i] = matched ? "YES" : "NO";
		}
		return values;
	}

	static boolean pop(final Stack<Character> stack, final char expectedToPop) {
		if (stack.isEmpty()) {
			return false;
		}
		return stack.pop() == expectedToPop;
	}
}
