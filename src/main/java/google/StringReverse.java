package google;

public class StringReverse {

	public static void main(String[] args) {
		System.out.println(reverse("Artur"));
		System.out.println(reverse("12345"));
		System.out.println(reverse("123456"));

		System.out.println("rec");
		
		System.out.println(recursive(new StringBuffer("Artur"), 0, "Artur".length() - 1));
		System.out.println(recursive(new StringBuffer("12345"), 0, "12345".length() - 1));
		System.out.println(recursive(new StringBuffer("123456"), 0, "123456".length() - 1));
	}
	
	
	static String reverse(String s) {
		char[] x = new char[s.length()];
		for(int i = 0; i < s.length() / 2; i++) {
			x[i] = s.charAt(s.length() - 1 - i);
			x[s.length() - 1 - i] = s.charAt(i);
		}
		if(s.length() %2 != 0) {
			x[s.length() / 2 ] = s.charAt(s.length() / 2 );
		}
		return new String(x);
	}
	
	static String recursive(StringBuffer b, int start, int end) {
		if(start >= end) {
			return b.toString();
		}
		char tmp = b.charAt(start);
		b.setCharAt(start, b.charAt(end));
		b.setCharAt(end, tmp);
		start++;
		end--;
		return recursive(b, start, end);
	}
}
